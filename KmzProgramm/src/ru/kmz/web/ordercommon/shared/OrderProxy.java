package ru.kmz.web.ordercommon.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderProxy implements Serializable {

	private String id;
	private String name;

	public OrderProxy() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderProxy(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
