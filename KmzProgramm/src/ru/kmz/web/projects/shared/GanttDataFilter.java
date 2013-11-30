package ru.kmz.web.projects.shared;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class GanttDataFilter implements Serializable {

	private List<String> orderIds;

	public GanttDataFilter() {

	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

}
