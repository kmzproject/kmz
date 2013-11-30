package ru.kmz.web.projects.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GanttDataFilter implements Serializable {

	private List<String> orderIds;

	public GanttDataFilter() {
		orderIds = new ArrayList<String>();
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void addOrderId(String orderId) {
		this.orderIds.add(orderId);
	}

	public void clearOrders() {
		this.orderIds.clear();
	}

}
