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
import ru.kmz.web.production.client.ProductionModuleService;
import ru.kmz.web.projects.server.ProjectsModuleServiceImpl;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projectscommon.server.ProjectsCommonServiceImpl;
import ru.kmz.web.projectscommon.shared.ProductionProxy;
import ru.test.DataTestEveryNew;

public class ProductionTest1 extends DataTestEveryNew {

	private ProductionModuleService service;
	private ProjectsModuleServiceImpl projectsService;

	@Before
	public void createService() {
		service = new ProjectsCommonServiceImpl();
		projectsService = new ProjectsModuleServiceImpl();
	}

	@Test
	public void SimpleTest() {
		Template template = TemplateTestData.createTemplateShort12();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		List<ProductionProxy> productions = service.getActiveProductions();

		Assert.assertEquals(3, productions.size());
		Assert.assertEquals("Сборка 1", productions.get(0).getName());
		Assert.assertEquals("A-001002", productions.get(0).getCode());
		Assert.assertEquals("Сборка 2", productions.get(1).getName());
		Assert.assertEquals("A-001003", productions.get(1).getCode());
		Assert.assertEquals("Сборка 3", productions.get(2).getName());
		Assert.assertEquals("A-001004", productions.get(2).getCode());

		Assert.assertEquals("Тестовый заказ 1", productions.get(0).getOrderName());
	}

	@Test
	public void SimpleCompliteTest() {
		Template template = TemplateTestData.createTemplateShort12();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		List<ProductionProxy> productions = service.getActiveProductions();

		Assert.assertEquals(3, productions.size());

		service.compliteProduction(productions.get(0).getId());

		productions = service.getActiveProductions();
		Assert.assertEquals(2, productions.size());

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

}
