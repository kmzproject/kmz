package ru.kmz.web.projects.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.test.DataTestEveryNew;

public class CalculateAndSaveUseWeekendTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
	}

	@Test
	public void saveUseWeekendTest1() {
		CalendarTestData.craeteCalendar1();
		Template template = TemplateTestData.createTemplateShort6();
		Order order = OrderTestData.createOrders1().get(0);

		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/11/06");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		input.setOrderId(order.getKeyStr());
		input.setUseWeekend(true);
		service.save(input);

		GanttData data = service.getCurrentTasks();

		Assert.assertEquals(DateUtils.getDate("2013/11/06"), data.getDateFinish());
		Assert.assertEquals(DateUtils.getDate("2013/11/03"), data.getDateStart());
	}

	@Test
	public void saveUseWeekendTest2() {
		CalendarTestData.craeteCalendar2();
		Template template = TemplateTestData.createTemplateShort6();
		Order order = OrderTestData.createOrders1().get(0);

		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/11/06");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		input.setOrderId(order.getKeyStr());
		input.setUseWeekend(true);
		service.save(input);

		GanttData data = service.getCurrentTasks();

		Assert.assertEquals(DateUtils.getDate("2013/11/06"), data.getDateFinish());
		Assert.assertEquals(DateUtils.getDate("2013/10/31"), data.getDateStart());

		GraphData rootOrder1 = data.getChilds().get(0);
		Assert.assertEquals(DateUtils.getDate("2013/11/06"), rootOrder1.getPlanFinish());
		Assert.assertEquals(DateUtils.getDate("2013/10/31"), rootOrder1.getPlanStart());
		Assert.assertEquals(6, rootOrder1.getDuration());

		GraphData rootProduct = rootOrder1.getChilds().get(0);
		Assert.assertEquals(DateUtils.getDate("2013/11/06"), rootProduct.getPlanFinish());
		Assert.assertEquals(DateUtils.getDate("2013/10/31"), rootProduct.getPlanStart());
		Assert.assertEquals(6, rootProduct.getDuration());

		GraphData element1 = rootProduct.getChilds().get(0);
		Assert.assertEquals("Сборка 1", element1.getName());
		Assert.assertEquals(DateUtils.getDate("2013/11/06"), element1.getPlanFinish());
		Assert.assertEquals(DateUtils.getDate("2013/11/05"), element1.getPlanStart());
		Assert.assertEquals(1, element1.getDuration());
		Assert.assertEquals(1, element1.getDurationWork());

		GraphData element2 = rootProduct.getChilds().get(1);
		Assert.assertEquals("Сборка 2", element2.getName());
		Assert.assertEquals(DateUtils.getDate("2013/11/06"), element2.getPlanFinish());
		Assert.assertEquals(DateUtils.getDate("2013/11/01"), element2.getPlanStart());
		Assert.assertEquals(5, element2.getDuration());
		Assert.assertEquals(2, element2.getDurationWork());
	}

}
