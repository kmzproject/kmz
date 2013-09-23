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

import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;

@Entity
public class ProducteTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	public ProducteTemplate() {
		childs = new ArrayList<ProducteTemplateElement>();
	}

	public ProducteTemplate(String name) {
		this();
		this.name = name;
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

	public void add(ProducteTemplateElement element) {
		this.childs.add(element);
	}

	public TemplateTreeNodeFolderProxy getProxy() {
		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(key.toString(), name, 0, "");
		for (ProducteTemplateElement element : childs) {
			proxy.add(element.asProxy());
		}
		return proxy;
	}
}
