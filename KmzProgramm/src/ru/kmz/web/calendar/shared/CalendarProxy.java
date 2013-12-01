package ru.kmz.web.calendar.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CalendarProxy implements Serializable {

	private long id;
	private String name;

	public CalendarProxy() {
	}

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

	public CalendarProxy(long id, String name) {
		this.id = id;
		this.name = name;
	}

}
