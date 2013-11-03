package ru.kmz.server.data.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.calendar.shared.CalendarRecordProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class CalendarRecord {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Date date;

	@Persistent
	private String comment;

	@Persistent
	private Key calendarId;

	public CalendarRecord(Key calendarId, Date date) {
		this(calendarId, date, null);
	}

	public CalendarRecord(Key calendarId, Date date, String commnet) {
		this.date = date;
		this.comment = commnet;
		this.calendarId = calendarId;
	}

	public CalendarRecordProxy asProxy() {
		return new CalendarRecordProxy(KeyFactory.keyToString(key), new Date(date.getTime()), comment);
	}
}
