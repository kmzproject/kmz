package ru.kmz.web.templatecommon.shared;

import java.io.Serializable;

import ru.kmz.web.common.shared.HasResourceType;

@SuppressWarnings("serial")
public class TemplateTreeNodeBaseProxy implements Serializable, HasResourceType {

	private Long id;

	private String name;

	private int duration;

	private String resourceType;

	public TemplateTreeNodeBaseProxy() {

	}

	public TemplateTreeNodeBaseProxy(Long id, String name, int duration, String resourceType) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.resourceType = resourceType;
	}

	public Long getId() {
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
			return ((TemplateTreeNodeBaseProxy) obj).id == id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id.intValue();
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
