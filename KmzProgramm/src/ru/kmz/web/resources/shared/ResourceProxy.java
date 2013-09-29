package ru.kmz.web.resources.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResourceProxy implements Serializable {

	private long id;
	private String name;
	private String resourceType;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceType() {
		return resourceType;
	}

	public ResourceProxy() {
	}

	public ResourceProxy(long id, String name, String resourceType) {
		this.id = id;
		this.name = name;
		this.resourceType = resourceType;
	}
}
