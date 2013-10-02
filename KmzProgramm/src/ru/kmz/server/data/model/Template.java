package ru.kmz.server.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;

@Entity
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	private ProducteTemplate product;

	public Template() {
	}

	public Template(String name) {
		this();
		this.name = name;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public ProducteTemplate getProduct() {
		return product;
	}

	public void setProduct(ProducteTemplate product) {
		this.product = product;
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

	public TemplateTreeDataProxy asProxy() {
		TemplateTreeNodeFolderProxy rootProxy = product.getProxy();
		TemplateTreeDataProxy proxy = new TemplateTreeDataProxy(key.getId(), name, rootProxy);
		return proxy;
	}

}
