package ru.kmz.web.projectscommon.server;

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
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.server.ProjectsModuleServiceImpl;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;
import ru.kmz.web.purchases.client.PurchasesModuleService;
import ru.test.DataTestEveryNew;

public class PurchasesTest1 extends DataTestEveryNew {

	private PurchasesModuleService service;
	private ProjectsModuleServiceImpl projectsService;

	@Before
	public void createService() {
		service = new ProjectsCommonServiceImpl();
		projectsService = new ProjectsModuleServiceImpl();
	}

	@Test
	public void SimpleTest() {
		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		List<PurchaseProxy> purchases = service.getActivePurchases(null);

		Assert.assertEquals(3, purchases.size());
		Assert.assertEquals("Покупка 1", purchases.get(0).getName());
		Assert.assertEquals("D-001002", purchases.get(0).getCode());
		Assert.assertEquals("Покупка 2", purchases.get(1).getName());
		Assert.assertEquals("D-001003", purchases.get(1).getCode());
		Assert.assertEquals("Покупка 3", purchases.get(2).getName());
		Assert.assertEquals("D-001004", purchases.get(2).getCode());

		Assert.assertEquals("Тестовый заказ 1", purchases.get(0).getOrderName());
	}

	@Test
	public void SimpleCompliteTest() {
		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		List<PurchaseProxy> purchases = service.getActivePurchases(null);

		Assert.assertEquals(3, purchases.size());

		service.complitePurchase(purchases.get(0).getId());

		purchases = service.getActivePurchases(null);
		Assert.assertEquals(2, purchases.size());

		GanttData ganttData = projectsService.getCurrentTasks();
		GraphData rootOrder = ganttData.getChilds().get(0);
		GraphData rootProducte = rootOrder.getChilds().get(0);
		GraphData element1 = rootProducte.getChilds().get(0);
		Assert.assertEquals(100, element1.getComplite());

		Assert.assertEquals(16, rootProducte.getComplite());
		Assert.assertEquals(16, rootOrder.getComplite());

		projectsService.save(input);
		ganttData = projectsService.getCurrentTasks();
		rootOrder = ganttData.getChilds().get(0);
		Assert.assertEquals(8, rootOrder.getComplite());
	}

	@Test
	public void taskStartedTest() {
		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		List<PurchaseProxy> purchases = service.getActivePurchases(null);

		Assert.assertEquals(3, purchases.size());
		Assert.assertEquals("Запланировано", purchases.get(0).getTaskState());

		service.setTaskAsStartedPersents(purchases.get(0).getId());

		purchases = service.getActivePurchases(null);
		Assert.assertEquals(3, purchases.size());
		Assert.assertEquals("В работе", purchases.get(0).getTaskState());
	}

	@Test
	public void testFilter() {
		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/20");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		ProductElementTaskGridFilter filter = new ProductElementTaskGridFilter();
		filter.setTo(DateUtils.getDate("2013/10/20"));
		List<PurchaseProxy> purchases = service.getActivePurchases(filter);
		Assert.assertEquals(3, purchases.size());

		filter.setTo(DateUtils.getDate("2013/10/19"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(3, purchases.size());

		filter.setTo(DateUtils.getDate("2013/10/18"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(2, purchases.size());

		filter.setTo(DateUtils.getDate("2013/10/17"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(1, purchases.size());

		filter.setTo(DateUtils.getDate("2013/10/16"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(0, purchases.size());

		filter.setTo(null);

		filter.setFrom(DateUtils.getDate("2013/10/16"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(3, purchases.size());

		filter.setFrom(DateUtils.getDate("2013/10/17"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(3, purchases.size());

		filter.setFrom(DateUtils.getDate("2013/10/18"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(3, purchases.size());

		filter.setFrom(DateUtils.getDate("2013/10/19"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(3, purchases.size());

		filter.setFrom(DateUtils.getDate("2013/10/20"));
		purchases = service.getActivePurchases(filter);
		Assert.assertEquals(0, purchases.size());

	}

}
