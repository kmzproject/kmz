package ru.kmz.web.projects.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.gantt.client.config.GanttConfig.TaskType;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.test.DataTestEveryNew;

public class NoDataProjectsService extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void getNoOrdersTest() {
		GanttData data = service.getCurrentTasks();
		Assert.assertEquals("Производство", data.getName());

		Date date = DateUtils.getDateNoTime(new Date());

		Assert.assertEquals(0, data.getChilds().size());
		Assert.assertEquals(date, data.getDateStart());
		Assert.assertEquals(date, data.getDateFinish());
	}

	@Test
	public void getOneEmpltOrderTest() {
		OrderTestData.createOrders1();

		GanttData data = service.getCurrentTasks();
		Assert.assertEquals("Производство", data.getName());

		Date date = DateUtils.getDateNoTime(new Date());
		Assert.assertEquals(date, data.getDateStart());
		Assert.assertEquals(date, data.getDateFinish());

		Assert.assertEquals(1, data.getChilds().size());
		GraphData order = data.getChilds().get(0);
		Assert.assertEquals("Тестовый заказ 1 (Z-001)", order.getName());
		Assert.assertEquals(TaskType.PARENT, order.getTaskType());
		Assert.assertEquals(date, order.getPlanStart());
		Assert.assertEquals(date, order.getPlanFinish());
	}

}
