package ru.kmz.server.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.appengine.api.datastore.Key;

@Entity
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	public Resource() {
	}

	public Resource(String name) {
		this();
		this.name = name;
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
	private String sercoureType;

	public String getSercoureType() {
		return sercoureType;
	}

	public void setSercoureType(String sercoureType) {
		this.sercoureType = sercoureType;
	}

	public ResourceProxy asProxy() {
		ResourceProxy proxy = new ResourceProxy();
		return proxy;
	}

}
