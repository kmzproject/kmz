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
import ru.kmz.web.calendar.server.CalendarModuleServiceImpl;
import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.UpdateProjectElementTaskParams;
import ru.test.DataTestEveryNew;

public class UpdateDateProductAndWeekendsTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;
	private CalendarModuleServiceImpl serviceCalendar;
	private Date projectFinishDate;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
		serviceCalendar = new CalendarModuleServiceImpl();
		prepareExample();
	}

	private void prepareExample() {
		projectFinishDate = DateUtils.getDate("2013/12/25");
		CalculateCalendarParamProxy calendarParams = new CalculateCalendarParamProxy(DateUtils.getDate("2013/12/01"), projectFinishDate);
		serviceCalendar.calculateWeekends(calendarParams);

		Date projectStartDate = DateUtils.getDate("2013/12/09");

		Template template = TemplateTestData.createTemplateShort9();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(projectFinishDate);
		input.setTemplateId(template.getId());
		input.setByFinishDate(true);
		input.setUseWeekend(true);
		input.setOrderId(order.getId());

		service.save(input);

		GanttData data = service.getCurrentTasks(null);

		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals(projectFinishDate, rootOrder.getPlanFinish());
		Assert.assertEquals(projectStartDate, rootOrder.getPlanStart());

		Assert.assertEquals(projectFinishDate, rootProduct.getPlanFinish());
		Assert.assertEquals(projectStartDate, rootProduct.getPlanStart());
	}

	@Test
	public void updateInNonWeekendDays() {
		// Нет выходхных в этот период
		Date newDateFinish = DateUtils.getDate("2013/11/25");
		Date newDateStart = DateUtils.getDate("2013/11/13");

		GanttData data = service.getCurrentTasks(null);
		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);

		UpdateProjectElementTaskParams params = new UpdateProjectElementTaskParams();
		params.setFinishDate(newDateFinish);
		service.updateDate(rootProduct.getId(), params);

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		rootProduct = rootOrder.getChilds().get(0);
		GraphData element1 = rootProduct.getChilds().get(0);
		GraphData element1_1 = element1.getChilds().get(0);
		GraphData element2 = rootProduct.getChilds().get(1);
		GraphData element3 = rootProduct.getChilds().get(2);
		Assert.assertEquals(newDateFinish, rootProduct.getPlanFinish());
		Assert.assertEquals(newDateStart, rootProduct.getPlanStart());
		Assert.assertEquals(newDateFinish, rootOrder.getPlanFinish());
		Assert.assertEquals(newDateStart, rootOrder.getPlanStart());
		Assert.assertEquals(newDateFinish, element1.getPlanFinish());
		Assert.assertEquals(DateUtils.getDate("2013/11/20"), element1.getPlanStart());
		Assert.assertEquals(DateUtils.getDate("2013/11/20"), element2.getPlanStart());
		Assert.assertEquals(DateUtils.getDate("2013/11/20"), element3.getPlanStart());
		Assert.assertEquals(DateUtils.getDate("2013/11/20"), element1_1.getPlanFinish());
		Assert.assertEquals(DateUtils.getDate("2013/11/13"), element1_1.getPlanStart());
	}

}
