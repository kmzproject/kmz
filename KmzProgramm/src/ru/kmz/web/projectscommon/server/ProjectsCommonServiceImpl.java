package ru.kmz.web.projectscommon.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.projects.CompliteTaskService;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.production.client.ProductionModuleService;
import ru.kmz.web.products.client.ProductsModuleService;
import ru.kmz.web.projectscommon.client.ProjectsCommonService;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.ProductProxy;
import ru.kmz.web.projectscommon.shared.ProductionProxy;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;
import ru.kmz.web.purchases.client.PurchasesModuleService;

@SuppressWarnings("serial")
public class ProjectsCommonServiceImpl extends AbstractServiceImpl implements PurchasesModuleService, ProductsModuleService, ProductionModuleService,
		ProjectsCommonService {

	@Override
	public void setCompliteTaskPersents(long id, int persents) {
		compliteProductElementTask(id, persents);
	}

	@Override
	public void setTaskAsStartedPersents(long id) {
		ProductElementTask task = ProductElementTaskDataUtils.getTask(id);
		if (task.setTaskStateAsStarted()) {
			ProductElementTaskDataUtils.edit(task);
			HistoryUtils.createTaskStarted(task);
		}
	}

	@Override
	public void setTaskAsPlannedPersents(long id) {
		ProductElementTask task = ProductElementTaskDataUtils.getTask(id);
		if (task.setTaskStatePlanned()) {
			ProductElementTaskDataUtils.edit(task);
			HistoryUtils.createTaskPlanned(task);
		}
	}

	@Override
	public List<PurchaseProxy> getActivePurchases(ProductElementTaskGridFilter filter) {
		if (filter == null) {
			filter = new ProductElementTaskGridFilter();
		}
		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedTask(ResourceTypes.PURCHASE, filter.getFrom(), filter.getTo());
		List<PurchaseProxy> proxyList = new ArrayList<PurchaseProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderId());

			PurchaseProxy proxy = task.asPurchaseProxy();
			proxy.setOrderNameAndCode(order.getNameAndCode());
			proxyList.add(proxy);
		}

		return proxyList;
	}

	@Override
	public void complitePurchase(long id) {
		compliteProductElementTask(id, 100);
	}

	@Override
	public List<ProductProxy> getActiveProducts(ProductElementTaskGridFilter filter) {
		if (filter == null) {
			filter = new ProductElementTaskGridFilter();
		}

		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedTask(ResourceTypes.PRODUCT, filter.getFrom(), filter.getTo());
		List<ProductProxy> proxyList = new ArrayList<ProductProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderId());

			ProductProxy proxy = task.asProductProxy();
			proxy.setOrderNameAndCode(order.getNameAndCode());
			proxyList.add(proxy);
		}
		return proxyList;
	}

	@Override
	public void compliteProduction(long id) {
		// TODO: добавить логику на проверку завершенности под закупок
		compliteProductElementTask(id, 100);
	}

	private void compliteProductElementTask(long id, int persents) {
		CompliteTaskService service = new CompliteTaskService(id);
		service.complite(persents);
	}

	@Override
	public List<ProductionProxy> getActiveProductions(ProductElementTaskGridFilter filter) {
		if (filter == null) {
			filter = new ProductElementTaskGridFilter();
		}

		List<ProductElementTask> list = ProductElementTaskDataUtils.getNotComplitedTask(ResourceTypes.ASSEMBLAGE, filter.getFrom(), filter.getTo());
		List<ProductionProxy> proxyList = new ArrayList<ProductionProxy>();

		for (ProductElementTask task : list) {
			Order order = OrderDataUtils.getOrder(task.getOrderId());

			ProductionProxy proxy = task.asProductionProxy();
			proxy.setOrderNameAndCode(order.getNameAndCode());
			proxyList.add(proxy);
		}
		return proxyList;
	}

}
