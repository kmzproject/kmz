package ru.kmz.web.projects.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.test.DataTestEveryNew;

public class IncludeCalendarDateTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void SimpleTest() {
		CalendarTestData.craeteCalendar2();

		GanttData data = service.getCurrentTasks(null);

		Assert.assertEquals(3, data.getCalendarRecords().size());

	}

}
