package ru.kmz.web.ordercommon.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.ordercommon.client.OrderCommonService;
import ru.kmz.web.ordercommon.shared.OrderProxy;

@SuppressWarnings("serial")
public class OrderCommonServiceImpl extends AbstractServiceImpl implements OrderCommonService {
	public List<OrderProxy> getOrders() {
		List<Order> orders = OrderDataUtils.getAllOrders();
		List<OrderProxy> result = new ArrayList<OrderProxy>();
		for (Order order : orders) {
			result.add(order.asProxy());
		}
		return result;
	}

	@Override
	public OrderProxy editOrder(OrderProxy proxy) {
		boolean isNew = proxy.getId() == 0;
		Order order;
		if (isNew) {
			order = new Order();
		} else {
			order = OrderDataUtils.getOrder(proxy.getId());
		}
		order.updateData(proxy);
		order = OrderDataUtils.edit(order);
		proxy = order.asProxy();
		if (isNew) {
			HistoryUtils.createOrder(order);
		} else {
			HistoryUtils.editOrder(order);
		}
		return proxy;
	}

}
