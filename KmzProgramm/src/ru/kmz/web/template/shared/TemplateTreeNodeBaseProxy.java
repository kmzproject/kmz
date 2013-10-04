package ru.kmz.web.template.shared;

import java.io.Serializable;

import ru.kmz.web.common.shared.HasResourceType;

@SuppressWarnings("serial")
public class TemplateTreeNodeBaseProxy implements Serializable, HasResourceType {

	private String id;

	private String name;

	private int duration;

	private String resourceType;

	public TemplateTreeNodeBaseProxy() {

	}

	public TemplateTreeNodeBaseProxy(String id, String name, int duration, String resourceType) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.resourceType = resourceType;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public String getResourceType() {
		return resourceType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TemplateTreeNodeBaseProxy) {
			return ((TemplateTreeNodeBaseProxy) obj).id.equals(id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public void setName(String name) {
		this.name = name;
	}
}
