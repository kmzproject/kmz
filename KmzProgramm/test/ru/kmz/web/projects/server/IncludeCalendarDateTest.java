package ru.kmz.web.projects.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.calendar.server.CalendarModuleServiceImpl;
import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.test.DataTestEveryNew;

public class IncludeCalendarDateTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;
	private CalendarModuleServiceImpl calendsrService;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
		calendsrService = new CalendarModuleServiceImpl();
	}

	@Test
	public void SimpleTest() {
		CalendarTestData.craeteCalendar2();

		GanttData data = service.getCurrentTasks(null);

		Assert.assertEquals(3, data.getCalendarRecords().size());

	}

	@Test
	public void addCalendarWeekendsTwise() {
		CalculateCalendarParamProxy param = new CalculateCalendarParamProxy(DateUtils.getDate("2013/12/01"), DateUtils.getDate("2013/12/31"));

		calendsrService.calculateWeekends(param);
		GanttData data = service.getCurrentTasks(null);
		Assert.assertEquals(9, data.getCalendarRecords().size());

		param = new CalculateCalendarParamProxy(DateUtils.getDate("2014/01/01"), DateUtils.getDate("2014/01/31"));
		calendsrService.calculateWeekends(param);
		data = service.getCurrentTasks(null);
		Assert.assertEquals(17, data.getCalendarRecords().size());
	}

}
