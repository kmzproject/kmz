package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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

	@Persistent(defaultFetchGroup = "true")
	private ProducteTemplateElement parent;

	@Persistent(mappedBy = "parent", defaultFetchGroup = "true")
	private List<ProducteTemplateElement> childs;

	public void updateData(TemplateTreeNodeBaseProxy proxy) {
		this.name = proxy.getName();
		this.duration = proxy.getDuration();
		this.resourceType = proxy.getResourceType();
	}

	public ProducteTemplateElement(String name, int duration, String resourseType) {
		this.name = name;
		this.duration = duration;
		this.resourceType = resourseType;
	}

	public void add(ProducteTemplateElement child) {
		if (childs == null) {
			childs = new ArrayList<ProducteTemplateElement>();
		}
		childs.add(child);
		child.parent = this;
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

	// public void add(ProducteTemplateElement element) {
	// if (elementIds == null) {
	// elementIds = new ArrayList<Key>();
	// }
	// elementIds.add(element.getKey());
	// }

	//
	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	public TemplateTreeNodeBaseProxy asProxy() {
		if (!hasChild()) {
			TemplateTreeNodeBaseProxy proxy = new TemplateTreeNodeBaseProxy(KeyFactory.keyToString(key), name,
					duration, resourceType);
			return proxy;
		}

		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(KeyFactory.keyToString(key), name,
				duration, resourceType);
		for (ProducteTemplateElement child : childs) {
			proxy.add(child.asProxy());
		}
		return proxy;
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

	public List<ProducteTemplateElement> getChilds() {
		return childs;
	}

	public ProducteTemplateElement getParent() {
		return parent;
	}
}
