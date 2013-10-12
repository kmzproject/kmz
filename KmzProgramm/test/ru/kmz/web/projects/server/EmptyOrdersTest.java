package ru.kmz.web.projects.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.test.DataTestEveryNew;

public class EmptyOrdersTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void getDateTest1() {
		GanttData data = service.getCurrentTasks();
		Assert.assertEquals("Производство", data.getName());

	}

}
