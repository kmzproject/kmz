package ru.kmz.server.data.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Template {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@NotPersistent
	private ProductTemplateElement rootElement;

	public ProductTemplateElement getRootElement() {
		return rootElement;
	}

	public Template(String name) {
		this.name = name;
	}

	public void setRootElement(ProductTemplateElement element) {
		rootElement = element;
	}

	public Key getKey() {
		return key;
	}

	public String getKeyStr() {
		return KeyFactory.keyToString(key);
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TemplateTreeDataProxy asProxy() {
		TemplateTreeNodeFolderProxy rootProxy = null;
		if (rootElement != null) {
			rootProxy = (TemplateTreeNodeFolderProxy) rootElement.asProxy();
		}
		TemplateTreeDataProxy proxy = new TemplateTreeDataProxy(getKeyStr(), name, rootProxy);
		return proxy;
	}

}
