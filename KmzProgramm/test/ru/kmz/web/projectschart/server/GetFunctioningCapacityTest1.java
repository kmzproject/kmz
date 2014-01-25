package ru.kmz.web.projectschart.server;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.calendar.client.CalendarModuleService;
import ru.kmz.web.calendar.server.CalendarModuleServiceImpl;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
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
	private CalendarModuleService calendarService;

	@Before
	public void createService() {
		service = new ProjectsChartServiceImpl();
		projectsService = new ProjectsModuleServiceImpl();
		calendarService = new CalendarModuleServiceImpl();
	}

	@Test
	public void getChartDataTest() {
		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date dateFinish = DateUtils.getDate("2013/11/10");
		input.setDate(dateFinish);
		input.setTemplateId(template.getId());
		long orderId = order.getId();
		input.setOrderId(orderId);
		projectsService.save(input);

		GanttData data = projectsService.getCurrentTasks(null);

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

	@Test
	public void getChartDataAndWeekendTest() {
		Template template = TemplateTestData.createTemplateShort5();
		Calendar calendar = CalendarTestData.craeteCalendar1();
		calendarService.createCalendarRecord(new CalendarRecordProxy(calendar.getId(), DateUtils.getDate("2013/11/08"), "A Day"));
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date dateFinish = DateUtils.getDate("2013/11/10");
		input.setDate(dateFinish);
		input.setTemplateId(template.getId());
		long orderId = order.getId();
		input.setOrderId(orderId);
		input.setUseWeekend(true);
		projectsService.save(input);

		GanttData data = projectsService.getCurrentTasks(null);

		Date dateStart = DateUtils.getDate("2013/11/06");

		Assert.assertEquals(dateStart, data.getDateStart());

		FunctioningCapacityParams params = new FunctioningCapacityParams();
		params.setFrom(dateStart);
		params.setTo(dateFinish);
		params.setResourceType(ResourceTypes.PURCHASE);

		List<FunctioningCapacityProxy> list = service.getFunctioningCapacity(params);
		Assert.assertEquals(4, list.size());

		Assert.assertEquals("11/06", list.get(0).getDay());
		Assert.assertEquals(1, list.get(0).getActivitiesCount());
		Assert.assertEquals("11/07", list.get(1).getDay());
		Assert.assertEquals(2, list.get(1).getActivitiesCount());
		Assert.assertEquals("11/08", list.get(2).getDay());
		Assert.assertEquals(0, list.get(2).getActivitiesCount());
		Assert.assertEquals("11/09", list.get(3).getDay());
		Assert.assertEquals(3, list.get(3).getActivitiesCount());

		params.setResourceType(ResourceTypes.PREPARE);

		list = service.getFunctioningCapacity(params);
		Assert.assertEquals(0, list.get(0).getActivitiesCount());
		Assert.assertEquals(0, list.get(1).getActivitiesCount());
		Assert.assertEquals(0, list.get(2).getActivitiesCount());
		Assert.assertEquals(0, list.get(3).getActivitiesCount());

		params.setFrom(DateUtils.getDate("2013/11/07"));
		params.setTo(DateUtils.getDate("2013/11/11"));
		params.setResourceType(ResourceTypes.PURCHASE);
		list = service.getFunctioningCapacity(params);
		Assert.assertEquals(4, list.size());
		Assert.assertEquals("11/07", list.get(0).getDay());
		Assert.assertEquals(2, list.get(0).getActivitiesCount());
		Assert.assertEquals("11/08", list.get(1).getDay());
		Assert.assertEquals(0, list.get(1).getActivitiesCount());
		Assert.assertEquals("11/09", list.get(2).getDay());
		Assert.assertEquals(3, list.get(2).getActivitiesCount());
		Assert.assertEquals("11/10", list.get(3).getDay());
		Assert.assertEquals(0, list.get(3).getActivitiesCount());
	}
}