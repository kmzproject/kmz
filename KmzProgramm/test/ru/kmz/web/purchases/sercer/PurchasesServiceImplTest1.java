package ru.kmz.web.purchases.sercer;

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
import ru.kmz.web.purchases.server.PurchasesModuleServiceImpl;
import ru.kmz.web.purchases.shared.PurchaseProxy;
import ru.test.DataTestEveryNew;

public class PurchasesServiceImplTest1 extends DataTestEveryNew {

	private PurchasesModuleServiceImpl service;
	private ProjectsModuleServiceImpl projectsService;

	@Before
	public void createService() {
		service = new PurchasesModuleServiceImpl();
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
		input.setByStartDate(true);
		input.setUseResource(false);
		String orderId = order.getKeyStr();
		projectsService.save(input, orderId);

		List<PurchaseProxy> purchases = service.getActivePurchases();

		Assert.assertEquals(3, purchases.size());
		Assert.assertEquals("Покупка 1", purchases.get(0).getName());
		Assert.assertEquals("Покупка 2", purchases.get(1).getName());
		Assert.assertEquals("Покупка 3", purchases.get(2).getName());

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
		input.setByStartDate(true);
		input.setUseResource(false);
		String orderId = order.getKeyStr();
		projectsService.save(input, orderId);

		List<PurchaseProxy> purchases = service.getActivePurchases();

		Assert.assertEquals(3, purchases.size());

		service.complitePurchase(purchases.get(0).getId());

		purchases = service.getActivePurchases();
		Assert.assertEquals(2, purchases.size());

		GanttData ganttData = projectsService.getCurrentTasks();
		GraphData rootOrder = ganttData.getChilds().get(0);
		GraphData rootProducte = rootOrder.getChilds().get(0);
		GraphData element1 = rootProducte.getChilds().get(0);
		Assert.assertEquals(100, element1.getComplite());

		Assert.assertEquals(16, rootProducte.getComplite());
		Assert.assertEquals(16, rootOrder.getComplite());
		
		projectsService.save(input, orderId);
		ganttData = projectsService.getCurrentTasks();
		rootOrder = ganttData.getChilds().get(0);
		Assert.assertEquals(8, rootOrder.getComplite());
	}

}