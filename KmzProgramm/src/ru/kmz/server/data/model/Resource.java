package ru.kmz.server.data.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.resources.shared.ResourceProxy;

@PersistenceCapable
public class Resource {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;
	@Persistent
	private String resourceType;

	public Resource() {
	}

	public Resource(ResourceProxy proxy) {
		name = proxy.getName();
		resourceType = proxy.getResourceType();
		if (proxy.getId() != 0) {
			id = proxy.getId();
		}
	}

	public Resource(String name, String resourceType) {
		this();
		this.name = name;
		this.resourceType = resourceType;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public ResourceProxy asProxy() {
		ResourceProxy proxy = new ResourceProxy(id, getName(), getResourceType());
		return proxy;
	}

	@Override
	public String toString() {
		return id + "" + name + " " + resourceType;
	}

}
