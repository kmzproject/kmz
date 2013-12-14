package ru.kmz.server.data.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.common.shared.TemplateTreeDataProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeFolderProxy;

@PersistenceCapable
public class Template {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

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

	public Long getId() {
		return id;
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
		TemplateTreeDataProxy proxy = new TemplateTreeDataProxy(id, name, rootProxy);
		return proxy;
	}

	@Override
	public String toString() {
		return name;
	}

}
