package ru.kmz.server.engine.projects;

import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.utils.OrderDataUtils;

public class GetOrdersService {

	public List<Order> getOrders() {
		List<Order> orders = OrderDataUtils.getAllOrders();
		for (Order order : orders) {
			OrderDataUtils.loadOrder(order);
		}
		return orders;
	}
}
