package ru.kmz.web.common.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class HistoryProxy implements Serializable {

	private long id;
	private String name;
	private String comment;
	private Date date;
	private String user;

	public HistoryProxy() {

	}

	public HistoryProxy(long id, String name, String comment, Date date, String user) {
		this.name = name;
		this.comment = comment;
		this.date = date;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public Date getDate() {
		return date;
	}

	public String getUser() {
		return user;
	}

//	@Override
//	public String toString() {
//		return String.format("%s %s %s %s", user, name, date, comment);
//	}
}
