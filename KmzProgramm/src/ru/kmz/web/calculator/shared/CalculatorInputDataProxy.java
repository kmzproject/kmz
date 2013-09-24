package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculatorInputDataProxy implements Serializable {

	private Date date;
	private boolean byFinishDate;
	private boolean byStartDate;

	public boolean isByFinishDate() {
		return byFinishDate;
	}

	public void setByFinishDate(boolean byFinishDate) {
		this.byFinishDate = byFinishDate;
	}

	public boolean isByStartDate() {
		return byStartDate;
	}

	public void setByStartDate(boolean byStartDate) {
		this.byStartDate = byStartDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
