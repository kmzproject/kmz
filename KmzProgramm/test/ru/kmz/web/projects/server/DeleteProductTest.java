package ru.kmz.web.projects.server;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.common.server.CommonServiceImpl;
import ru.kmz.web.common.shared.HistoryProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projectscommon.server.ProjectsCommonServiceImpl;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;
import ru.kmz.web.purchases.client.PurchasesModuleService;
import ru.test.DataTestEveryNew;

public class DeleteProductTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;
	private PurchasesModuleService purchaseService;
	private CommonServiceImpl commonService;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
		purchaseService = new ProjectsCommonServiceImpl();
		commonService = new CommonServiceImpl();
	}

	@Test
	public void SimpleTest() {
		Date date = DateUtils.getDate("2013/10/01");

		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(date);
		input.setTemplateId(template.getId());
		input.setByFinishDate(true);
		input.setOrderId(order.getId());

		service.save(input);

		GanttData data = service.getCurrentTasks(null);

		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);

		List<PurchaseProxy> purchases = purchaseService.getActivePurchases(null);
		Assert.assertEquals(3, purchases.size());
		purchaseService.complitePurchase(purchases.get(0).getId());
		List<HistoryProxy> history = commonService.getHistoryByObject(null);
		Assert.assertEquals(history.toString(), 2, history.size());
		Assert.assertEquals("D-001002 Покупка 1 было 0 стало 100", history.get(0).getComment());

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		Assert.assertEquals(16, rootOrder.getComplite());

		service.deleteProduct(rootProduct.getId());

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		Assert.assertEquals(0, rootOrder.getChilds().size());
		Assert.assertEquals(0, rootOrder.getComplite());

		purchases = purchaseService.getActivePurchases(null);
		Assert.assertEquals(0, purchases.size());

		history = commonService.getHistoryByObject(null);
		Assert.assertEquals(history.toString(), 3, history.size());
		Assert.assertEquals("I-001001 Изделие Short5 удалено из производства", history.get(0).getComment());
	}

}
