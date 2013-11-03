package ru.kmz.web.calendar.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CalendarProxy implements Serializable {

	private String id;
	private String name;

	public CalendarProxy() {
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

	public CalendarProxy(String id, String name) {
		this.id = id;
		this.name = name;
	}

}
