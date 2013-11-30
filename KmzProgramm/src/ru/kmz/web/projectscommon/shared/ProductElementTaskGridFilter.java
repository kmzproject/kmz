package ru.kmz.web.projectscommon.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ProductElementTaskGridFilter implements Serializable {

	private Date from;
	private Date to;

	public ProductElementTaskGridFilter() {
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
