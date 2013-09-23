package ru.kmz.web.template.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeNodeBaseProxy implements Serializable {

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

}
