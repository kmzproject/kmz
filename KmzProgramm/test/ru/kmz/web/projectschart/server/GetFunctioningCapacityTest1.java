package ru.kmz.web.projectschart.server;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.client.ProjectsModuleService;
import ru.kmz.web.projects.server.ProjectsModuleServiceImpl;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projectschart.shared.FunctioningCapacityParams;
import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;
import ru.test.DataTestEveryNew;

public class GetFunctioningCapacityTest1 extends DataTestEveryNew {

	private ProjectsChartServiceImpl service;
	private ProjectsModuleService projectsService;

	@Before
	public void createService() {
		service = new ProjectsChartServiceImpl();
		projectsService = new ProjectsModuleServiceImpl();
	}

	@Test
	public void saveUseWeekendTest1() {
		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date dateFinish = DateUtils.getDate("2013/11/10");
		input.setDate(dateFinish);
		input.setTemplateId(template.getKeyStr());
		String orderId = order.getKeyStr();
		input.setOrderId(orderId);
		projectsService.save(input);

		GanttData data = projectsService.getCurrentTasks();

		Date dateStart = DateUtils.getDate("2013/11/07");

		Assert.assertEquals(dateStart, data.getDateStart());

		FunctioningCapacityParams params = new FunctioningCapacityParams();
		params.setFrom(dateStart);
		params.setTo(dateFinish);
		params.setResourceType(ResourceTypes.PURCHASE);

		List<FunctioningCapacityProxy> list = service.getFunctioningCapacity(params);
		Assert.assertEquals(3, list.size());

		Assert.assertEquals(1, list.get(0).getActivitiesCount());
		Assert.assertEquals(2, list.get(1).getActivitiesCount());
		Assert.assertEquals(3, list.get(2).getActivitiesCount());

		params.setResourceType(ResourceTypes.PREPARE);

		list = service.getFunctioningCapacity(params);
		Assert.assertEquals(0, list.get(0).getActivitiesCount());
		Assert.assertEquals(0, list.get(1).getActivitiesCount());
		Assert.assertEquals(0, list.get(2).getActivitiesCount());

	}
}