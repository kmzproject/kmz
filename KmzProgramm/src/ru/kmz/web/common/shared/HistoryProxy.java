package ru.kmz.web.common.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class HistoryProxy implements Serializable {

	private String name;
	private String comment;
	private Date date;

	public HistoryProxy() {

	}

	public HistoryProxy(String name, String comment, Date date) {
		this.name = name;
		this.comment = comment;
		this.date = date;
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

}
