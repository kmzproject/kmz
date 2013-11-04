package ru.kmz.server.engine.production;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.web.production.shared.ProductionProxy;

public class ProductionListUtils {

	public static List<ProductionProxy> getActiveProduction() {
		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedAssemblages();
		List<ProductionProxy> proxyList = new ArrayList<ProductionProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderIdStr());

			ProductionProxy proxy = task.asProductionProxy();
			proxy.setOrderName(order.getName());
			proxyList.add(proxy);
		}

		return proxyList;
	}
}
