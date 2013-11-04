package ru.kmz.web.calendar.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculateCalendarParamProxy implements Serializable {

	private Date from;
	private Date to;

	public CalculateCalendarParamProxy() {

	}

	public CalculateCalendarParamProxy(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}
}
