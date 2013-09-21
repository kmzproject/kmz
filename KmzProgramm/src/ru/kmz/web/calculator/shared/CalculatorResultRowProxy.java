package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculatorResultRowProxy implements Serializable {

	private Integer id;
	private Date startDate;
	private Date finishDate;
	private String name;

	public CalculatorResultRowProxy() {
	}

	public CalculatorResultRowProxy(Integer id, String name, Date startDate, Date finishDate) {
		this();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.finishDate = finishDate;
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

	public String toString() {
		return getName();
	}
}
