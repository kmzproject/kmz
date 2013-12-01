package ru.kmz.web.projects.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GanttDataFilter implements Serializable {

	private List<Long> orderIds;

	public GanttDataFilter() {
		orderIds = new ArrayList<Long>();
	}

	public List<Long> getOrderIds() {
		return orderIds;
	}

	public void addOrderId(long orderId) {
		this.orderIds.add(orderId);
	}

	public void clearOrders() {
		this.orderIds.clear();
	}

}
