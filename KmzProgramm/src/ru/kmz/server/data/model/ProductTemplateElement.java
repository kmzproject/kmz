package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class ProductTemplateElement {

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
	private Key parentId;

	@Persistent
	private Key templateId;

	@NotPersistent
	private List<ProductTemplateElement> childs;

	public void updateData(TemplateTreeNodeBaseProxy proxy) {
		this.name = proxy.getName();
		this.duration = proxy.getDuration();
		this.resourceType = proxy.getResourceType();
	}

	public ProductTemplateElement(String name, int duration, String resourseType, Template template) {
		this.name = name;
		this.duration = duration;
		this.resourceType = resourseType;
		this.parentId = null;
		this.templateId = template.getKey();

		template.setRootElement(this);
	}

	public ProductTemplateElement(String name, int duration, String resourseType, ProductTemplateElement parent) {
		this.name = name;
		this.duration = duration;
		this.resourceType = resourseType;
		this.parentId = parent.getKey();
		this.templateId = parent.getTemplateId();

		parent.add(this);
	}

	public Key getParentId() {
		return parentId;
	}

	public Key getTemplateId() {
		return templateId;
	}

	public void add(ProductTemplateElement child) {
		if (childs == null) {
			childs = new ArrayList<ProductTemplateElement>();
		}
		childs.add(child);
		child.parentId = key;
	}

	public Key getKey() {
		return key;
	}

	public Key getParentKey() {
		return parentId;
	}

	public String getKeyStr() {
		return KeyFactory.keyToString(key);
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	public TemplateTreeNodeBaseProxy asProxy() {
		if (!hasChild()) {
			TemplateTreeNodeBaseProxy proxy = new TemplateTreeNodeBaseProxy(getKeyStr(), name, duration, resourceType);
			return proxy;
		}

		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(getKeyStr(), name, duration, resourceType);
		for (ProductTemplateElement child : childs) {
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

	public List<ProductTemplateElement> getChilds() {
		return childs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductTemplateElement) {
			return ((ProductTemplateElement) obj).key.equals(key);
		}
		return false;
	}
}
