package ru.kmz.server.data.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Resource {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

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
			Key k = KeyFactory.createKey(getClass().getSimpleName(), proxy.getId());
			setKey(k);
		}
	}

	public Resource(String name, String resourceType) {
		this();
		this.name = name;
		this.resourceType = resourceType;
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public ResourceProxy asProxy() {
		ResourceProxy proxy = new ResourceProxy(getKey().getId(), getName(), getResourceType());
		return proxy;
	}

	@Override
	public String toString() {
		return key + "" + name + " " + resourceType;
	}

}
