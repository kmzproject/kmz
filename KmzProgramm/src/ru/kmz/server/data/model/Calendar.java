package ru.kmz.server.data.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.calendar.shared.CalendarProxy;

@PersistenceCapable
public class Calendar {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;

	public Calendar(String name) {
		this.name = name;
	}

	public CalendarProxy asProxy() {
		return new CalendarProxy(id, name);
	}

	public Long getId() {
		return id;
	}
}
