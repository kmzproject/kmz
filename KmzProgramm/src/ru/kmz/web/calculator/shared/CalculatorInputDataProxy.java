package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculatorInputDataProxy implements Serializable {

	private Date date;
	private boolean byFinishDate;
	private boolean byStartDate;
	private String scala;
	private String templateId;

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

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
