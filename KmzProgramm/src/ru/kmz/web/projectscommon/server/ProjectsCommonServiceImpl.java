package ru.kmz.web.projectscommon.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.production.client.ProductionModuleService;
import ru.kmz.web.products.client.ProductsModuleService;
import ru.kmz.web.projectscommon.client.ProjectsCommonService;
import ru.kmz.web.projectscommon.shared.ProductProxy;
import ru.kmz.web.projectscommon.shared.ProductionProxy;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;
import ru.kmz.web.purchases.client.PurchasesModuleService;

@SuppressWarnings("serial")
public class ProjectsCommonServiceImpl extends AbstractServiceImpl implements PurchasesModuleService, ProductsModuleService, ProductionModuleService,
		ProjectsCommonService {

	@Override
	public void setCompliteTaskPersents(String id, int persents) {
		ProductElementTaskDataUtils.setTaskComplitePersents(id, persents);
	}

	@Override
	public void setTaskAsStartedPersents(String id) {
		ProductElementTask task = ProductElementTaskDataUtils.getTask(id);
		task.setTaskStateAsStarted();
		ProductElementTaskDataUtils.edit(task);
		HistoryUtils.createTaskStarted(task);
	}

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

	@Override
	public void compliteProduction(String id) {
		// TODO: добавить логику на проверку завершенности под закупок
		ProductElementTaskDataUtils.setTaskComplitePersents(id, 100);
	}

	@Override
	public List<ProductionProxy> getActiveProductions() {
		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedTask(ResourceTypes.ASSEMBLAGE);
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
