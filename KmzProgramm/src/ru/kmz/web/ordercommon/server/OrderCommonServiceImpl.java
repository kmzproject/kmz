package ru.kmz.web.ordercommon.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.web.ordercommon.client.OrderCommonService;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class OrderCommonServiceImpl extends RemoteServiceServlet implements OrderCommonService {

	public List<OrderProxy> getOrders() {
		List<Order> orders = OrderDataUtils.getAllOrders();
		List<OrderProxy> result = new ArrayList<OrderProxy>();
		for (Order order : orders) {
			result.add(order.asProxy());
		}
		return result;
	}

}
