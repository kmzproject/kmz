package ru.kmz.server.engine.projects;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.utils.OrderDataUtils;

public class GetOrdersService {

	private List<Long> orderIds;

	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
	}

	public List<Order> getOrders() {
		List<Order> orders = getFilteredOrdersList();
		for (Order order : orders) {
			OrderDataUtils.loadOrder(order);
		}
		return orders;
	}

	private List<Order> getFilteredOrdersList() {
		List<Order> orders = OrderDataUtils.getAllOrders();
		if (orderIds.isEmpty()) {
			return orders;
		}

		List<Order> filteredList = new ArrayList<Order>();
		for (Order order : orders) {
			if (orderIds.contains(order.getId())) {
				filteredList.add(order);
			}
		}
		return filteredList;
	}
}
