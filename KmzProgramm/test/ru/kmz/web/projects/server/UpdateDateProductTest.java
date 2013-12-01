package ru.kmz.web.projects.server;

import java.util.Date;

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

public class UpdateDateProductTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void SimpleTest() {
		Date date = DateUtils.getDate("2013/10/01");
		Date newDate = DateUtils.getDate("2013/10/15");

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
		Assert.assertEquals(date, rootProduct.getPlanFinish());

		service.updateDate(rootProduct.getId(), newDate);

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		rootProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals(newDate, rootProduct.getPlanFinish());

	}

}
