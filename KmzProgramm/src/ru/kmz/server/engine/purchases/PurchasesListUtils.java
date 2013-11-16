package ru.kmz.server.engine.purchases;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

public class PurchasesListUtils {

	public static List<PurchaseProxy> getActivePurchases() {
		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedOrders();
		List<PurchaseProxy> proxyList = new ArrayList<PurchaseProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderIdStr());

			PurchaseProxy proxy = task.asPurchaseProxy();
			proxy.setOrderName(order.getName());
			proxyList.add(proxy);
		}

		return proxyList;
	}
}
