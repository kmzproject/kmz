package ru.kmz.server.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

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

	private String name;
	private String resourceType;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Transient
	public ResourceProxy asProxy() {
		ResourceProxy proxy = new ResourceProxy(getKey().getId(), getName(), getResourceType());
		return proxy;
	}

	@Override
	public String toString() {
		return key + "" + name + " " + resourceType;
	}

}
