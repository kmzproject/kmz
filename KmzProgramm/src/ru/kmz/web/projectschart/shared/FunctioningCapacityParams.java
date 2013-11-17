package ru.kmz.web.projectschart.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FunctioningCapacityParams implements Serializable {

	private Date from;
	private Date to;
	private String resourceType;

	public FunctioningCapacityParams() {
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

}
