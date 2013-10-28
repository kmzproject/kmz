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

public class DeleteProductTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void SimpleSetPersentTest() {
		Date date = DateUtils.getDate("2013/10/01");

		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(DateUtils.getOffsetDate(date, 1));
		input.setTemplateId(template.getKeyStr());
		input.setByFinishDate(true);
		input.setOrderId(order.getKeyStr());

		service.save(input);

		GanttData data = service.getCurrentTasks();

		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);

		service.deleteProduct(rootProduct.getId());

		data = service.getCurrentTasks();
		rootOrder = data.getChilds().get(0);
		Assert.assertEquals(0, rootOrder.getChilds().size());

	}

}
