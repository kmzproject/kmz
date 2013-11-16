package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.server.data.constants.ResourceTypes;
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

	@Persistent
	private int orderNum;

	@NotPersistent
	private List<ProductTemplateElement> childs;

	public void updateData(TemplateTreeNodeBaseProxy proxy) {
		this.name = proxy.getName();
		this.duration = proxy.getDuration();
		this.resourceType = proxy.getResourceType();
	}

	public ProductTemplateElement(String name, Template template) {
		this.name = name;
		this.duration = 0;
		this.resourceType = ResourceTypes.PRODUCT;
		this.parentId = null;
		this.templateId = template.getKey();
		orderNum = 0;

		template.setRootElement(this);
	}

	public ProductTemplateElement(String name, int duration, String resourseType, ProductTemplateElement parent) {
		this.name = name;
		this.duration = duration;
		this.resourceType = resourseType;
		this.parentId = parent.getKey();
		this.templateId = parent.getTemplateId();

		if (parent.hasChild()) {
			orderNum = parent.getChilds().size();
		} else {
			orderNum = 0;
		}

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

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	public TemplateTreeNodeFolderProxy asProxy() {
		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(getKeyStr(), name, duration, resourceType);
		if (hasChild()) {
			for (ProductTemplateElement child : childs) {
				proxy.add(child.asProxy());
			}
		}
		return proxy;
	}

	public GraphData asGraphDataProxy() {
		return new GraphData(getKeyStr(), name, null, duration, duration, resourceType);
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

	@Override
	public String toString() {
		return name;
	}
}
