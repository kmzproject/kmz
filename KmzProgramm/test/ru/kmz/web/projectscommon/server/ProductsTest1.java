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
import ru.kmz.web.products.client.ProductsModuleService;
import ru.kmz.web.projects.server.ProjectsModuleServiceImpl;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projectscommon.shared.ProductProxy;
import ru.test.DataTestEveryNew;

public class ProductsTest1 extends DataTestEveryNew {

	private ProductsModuleService service;
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
		Date date = DateUtils.getDate("2013/10/10");
		input.setDate(date);
		input.setTemplateId(template.getId());
		input.setOrderId(order.getId());
		projectsService.save(input);

		List<ProductProxy> products = service.getActiveProducts(null);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("Изделие Short5", products.get(0).getName());
		Assert.assertEquals("I-001001", products.get(0).getCode());
		Assert.assertEquals(DateUtils.getDate("2013/10/07"), products.get(0).getPlanStart());
		Assert.assertEquals(date, products.get(0).getPlanFinish());

		Assert.assertEquals("Тестовый заказ 1 (Z-001)", products.get(0).getOrderNameAndCode());
	}
}
