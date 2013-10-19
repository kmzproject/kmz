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
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.test.DataTestEveryNew;

public class CheckOrder extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void toProductInOneOrder() {
		Date date = DateUtils.getDate("2013/10/01");

		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(DateUtils.getOffsetDate(date, 1));
		input.setTemplateId(template.getKeyStr());
		input.setByFinishDate(true);
		input.setUseResource(false);
		input.setShowOtherTasks(true);
		String orderId = order.getKeyStr();

		service.save(input, orderId);

		input.setDate(date);
		service.save(input, orderId);

		GanttData data = service.getCurrentTasks();

		Assert.assertEquals(1, data.getChilds().size());
		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct1 = rootOrder.getChilds().get(0);
		GraphData rootProduct2 = rootOrder.getChilds().get(1);

		Date startDate = DateUtils.getOffsetDate(date, -3);

		Assert.assertEquals(startDate, rootOrder.getPlanStart());

		Assert.assertEquals(startDate, rootProduct1.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(startDate, 1), rootProduct2.getPlanStart());
	}

	@Test
	public void treeOrdersByOneProduct() {
		Date date = DateUtils.getDate("2013/10/01");

		Template template = TemplateTestData.createTemplateShort5();
		List<Order> orders = OrderTestData.createOrders3();
		Order order1 = orders.get(0);
		Order order2 = orders.get(1);
		Order order3 = orders.get(2);

		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(DateUtils.getOffsetDate(date, 2));
		input.setTemplateId(template.getKeyStr());
		input.setByFinishDate(true);
		input.setUseResource(false);
		input.setShowOtherTasks(true);

		service.save(input, order1.getKeyStr());

		input.setDate(DateUtils.getOffsetDate(date, 1));
		service.save(input, order2.getKeyStr());

		input.setDate(date);
		service.save(input, order3.getKeyStr());

		GanttData data = service.getCurrentTasks();

		Assert.assertEquals(3, data.getChilds().size());
		GraphData rootOrder1 = data.getChilds().get(0);
		GraphData rootOrder2 = data.getChilds().get(1);
		GraphData rootOrder3 = data.getChilds().get(2);

		Date startDate = DateUtils.getOffsetDate(date, -3);

		Assert.assertEquals(startDate, rootOrder1.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(startDate, 1), rootOrder2.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(startDate, 2), rootOrder3.getPlanStart());
	}

	@Test
	public void treeOrdersByOneProductAndCalculate() {
		Date date = DateUtils.getDate("2013/10/01");

		Template template = TemplateTestData.createTemplateShort5();
		List<Order> orders = OrderTestData.createOrders3();
		Order order1 = orders.get(0);
		Order order2 = orders.get(1);
		Order order3 = orders.get(2);

		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(DateUtils.getOffsetDate(date, 2));
		input.setTemplateId(template.getKeyStr());
		input.setByFinishDate(true);
		input.setUseResource(false);
		input.setShowOtherTasks(true);

		service.save(input, order1.getKeyStr());

		input.setDate(DateUtils.getOffsetDate(date, 1));
		service.save(input, order2.getKeyStr());

		input.setDate(date);
		service.save(input, order3.getKeyStr());

		input.setDate(DateUtils.getOffsetDate(date, 3));
		GanttData data = service.getGantResultData(input);

		Assert.assertEquals(4, data.getChilds().size());
		GraphData rootTemplate = data.getChilds().get(0);
		GraphData rootOrder1 = data.getChilds().get(1);
		GraphData rootOrder2 = data.getChilds().get(2);
		GraphData rootOrder3 = data.getChilds().get(3);

		Date startDate = DateUtils.getOffsetDate(date, -3);

		Assert.assertEquals("Изделие Short5", rootTemplate.getName());

		Assert.assertEquals(startDate, rootOrder1.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(startDate, 1), rootOrder2.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(startDate, 2), rootOrder3.getPlanStart());

		Assert.assertEquals(DateUtils.getOffsetDate(startDate, 3), rootTemplate.getPlanStart());
	}

}