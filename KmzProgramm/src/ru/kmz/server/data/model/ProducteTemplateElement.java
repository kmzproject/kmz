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
import javax.persistence.Transient;

import ru.kmz.web.ganttcommon.shared.GraphData;
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

	@Transient
	public void setProxyData(TemplateTreeNodeBaseProxy proxy) {
		this.name = proxy.getName();
		this.duration = proxy.getDuration();
		this.resourceType = proxy.getResourceType();
	}

	public ProducteTemplateElement(String name, int duration, String resourseType) {
		this();
		this.name = name;
		this.duration = duration;
		this.resourceType = resourseType;
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

	private int duration;

	public int getDuration() {
		return duration;
	}

	private String resourceType;

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Transient
	public void add(ProducteTemplateElement element) {
		if (childs == null) {
			childs = new ArrayList<ProducteTemplateElement>();
		}
		this.childs.add(element);
	}

	@Transient
	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	@Transient
	public TemplateTreeNodeBaseProxy asProxy() {
		if (!hasChild()) {
			TemplateTreeNodeBaseProxy proxy = new TemplateTreeNodeBaseProxy(key.getId(), name, duration, resourceType);
			return proxy;
		}

		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(key.getId(), name, duration, resourceType);
		for (ProducteTemplateElement child : childs) {
			proxy.add(child.asProxy());
		}
		return proxy;
	}

	@Transient
	public GraphData asGraphDataProxy() {
		return new GraphData("wbs_" + key, name, duration, resourceType);
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
}
