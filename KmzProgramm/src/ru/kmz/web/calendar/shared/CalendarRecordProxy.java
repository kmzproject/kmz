package ru.kmz.web.calendar.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalendarRecordProxy implements Serializable {

	private long id;
	private Date date;
	private String comment;

	public CalendarRecordProxy() {
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CalendarRecordProxy(long id, Date date, String comment) {
		this.id = id;
		this.date = date;
		this.comment = comment;
	}

}
