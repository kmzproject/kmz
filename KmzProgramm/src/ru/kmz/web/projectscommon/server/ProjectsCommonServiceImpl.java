package ru.kmz.web.projectscommon.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.web.products.client.ProductsModuleService;
import ru.kmz.web.projectscommon.shared.ProductProxy;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;
import ru.kmz.web.purchases.client.PurchasesModuleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ProjectsCommonServiceImpl extends RemoteServiceServlet implements PurchasesModuleService, ProductsModuleService {

	@Override
	public List<PurchaseProxy> getActivePurchases() {
		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedTask(ResourceTypes.PURCHASE);
		List<PurchaseProxy> proxyList = new ArrayList<PurchaseProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderIdStr());

			PurchaseProxy proxy = task.asPurchaseProxy();
			proxy.setOrderName(order.getName());
			proxyList.add(proxy);
		}

		return proxyList;
	}

	@Override
	public void complitePurchase(String id) {
		ProductElementTaskDataUtils.setTaskComplitePersents(id, 100);
	}

	@Override
	public List<ProductProxy> getActiveProducts() {
		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedTask(ResourceTypes.PRODUCT);
		List<ProductProxy> proxyList = new ArrayList<ProductProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderIdStr());

			ProductProxy proxy = task.asProductProxy();
			proxy.setOrderName(order.getName());
			proxyList.add(proxy);
		}
		return proxyList;
	}

}
