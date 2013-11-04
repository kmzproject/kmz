package ru.kmz.web.projects.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculatorInputDataProxy implements Serializable {

	private Date date;
	private boolean byFinishDate;
	private boolean byStartDate;
	private boolean useResource;
	private String templateId;
	private String orderId;
	private int count;
	private boolean useWeekend;

	public CalculatorInputDataProxy() {
		byFinishDate = true;
		count = 1;
	}

	public boolean isByFinishDate() {
		return byFinishDate;
	}

	public void setByFinishDate(boolean byFinishDate) {
		this.byFinishDate = byFinishDate;
		if (byFinishDate) {
			byStartDate = false;
		}
	}

	public boolean isByStartDate() {
		return byStartDate;
	}

	public void setByStartDate(boolean byStartDate) {
		this.byStartDate = byStartDate;
		if (byStartDate) {
			byFinishDate = false;
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public boolean isUseResource() {
		return useResource;
	}

	public void setUseResource(boolean useResource) {
		this.useResource = useResource;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public boolean isUseWeekend() {
		return useWeekend;
	}

	public void setUseWeekend(boolean useWeekend) {
		this.useWeekend = useWeekend;
	}

}
