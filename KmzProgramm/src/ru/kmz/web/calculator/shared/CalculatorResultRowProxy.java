package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculatorResultRowProxy implements Serializable {

	private Integer id;
	private Date startDate;
	private Date finishDate;
	private String name;
	private String resourceType;
	private int duration;

	public CalculatorResultRowProxy() {
	}

	public CalculatorResultRowProxy(Integer id, String name, String resourceType, Date startDate, Date finishDate,
			int duration) {
		this();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.resourceType = resourceType;
		this.duration = duration;
	}

	public Integer getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public void getFinishDate(Date date) {
		this.finishDate = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public String toString() {
		return getName();
	}

	public int getDuration() {
		return duration;
	}
}
