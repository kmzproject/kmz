package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ProducteTemplateElement {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private int duration;

	@Persistent
	private String resourceType;

	@Persistent
	private List<Key> elements;

	public ProducteTemplateElement() {
	}

	public void updateData(TemplateTreeNodeBaseProxy proxy) {
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

	public List<Key> getElements() {
		return elements;
	}

	public void setElements(List<Key> elements) {
		this.elements = elements;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void add(ProducteTemplateElement element) {
		if (elements == null) {
			elements = new ArrayList<Key>();
		}
		this.elements.add(element.getKey());
	}

	public boolean hasChild() {
		return elements != null && elements.size() != 0;
	}

	public TemplateTreeNodeBaseProxy asProxy() {
		// if (!hasChild()) {
		// TemplateTreeNodeBaseProxy proxy = new
		// TemplateTreeNodeBaseProxy(key.getId(), name, duration, resourceType);
		// return proxy;
		// }
		//
		// TemplateTreeNodeFolderProxy proxy = new
		// TemplateTreeNodeFolderProxy(key.getId(), name, duration,
		// resourceType);
		// // for (ProducteTemplateElement child : elements) {
		// // proxy.add(child.asProxy());
		// // }
		// return proxy;
		return null;
	}

	public GraphData asGraphDataProxy() {
		return new GraphData(key.getId() + "", name, duration, resourceType);
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
}
