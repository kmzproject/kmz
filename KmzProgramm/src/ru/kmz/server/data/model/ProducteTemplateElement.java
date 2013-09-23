package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;

@Entity
public class ProducteTemplateElement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	public ProducteTemplateElement() {
		childs = new ArrayList<ProducteTemplateElement>();
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProducteTemplateElement> childs;

	public List<ProducteTemplateElement> getChilds() {
		return childs;
	}

	public void setChilds(List<ProducteTemplateElement> childs) {
		this.childs = childs;
	}

	public Key getKey() {
		return key;
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

	private String name;

	public TemplateTreeNodeBaseProxy asProxy() {
		if (childs == null || childs.size() == 0) {
			TemplateTreeNodeBaseProxy proxy = new TemplateTreeNodeBaseProxy(key.toString(), name);
			return proxy;
		}

		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(key.toString(), name);
		for (ProducteTemplateElement child : childs) {
			proxy.add(child.asProxy());
		}
		return proxy;
	}
}
