package ru.kmz.web.resources.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResourceProxy implements Serializable {

	private String id;
	private String name;
	private String resourceType;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public ResourceProxy() {
	}

	public ResourceProxy(String id, String name, String resourceType) {
		this.id = id;
		this.name = name;
		this.resourceType = resourceType;
	}
}
