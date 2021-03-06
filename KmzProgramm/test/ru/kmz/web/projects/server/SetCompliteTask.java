package ru.kmz.web.projects.server;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.common.server.CommonServiceImpl;
import ru.kmz.web.common.shared.HistoryProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projectscommon.server.ProjectsCommonServiceImpl;
import ru.test.DataTestEveryNew;

public class SetCompliteTask extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;
	private CommonServiceImpl commonService;
	private ProjectsCommonServiceImpl projectsCommonService;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
		commonService = new CommonServiceImpl();
		projectsCommonService = new ProjectsCommonServiceImpl();
	}

	@Test
	public void SimpleSetPersentTest() {
		Date date = DateUtils.getDate("2013/10/01");

		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(DateUtils.getOffsetDate(date, 1));
		input.setTemplateId(template.getId());
		input.setByFinishDate(true);
		input.setOrderId(order.getId());

		service.save(input);

		GanttData data = service.getCurrentTasks(null);

		GraphData rootProductElement = getElement(data);

		Assert.assertEquals(0, rootProductElement.getComplite());

		projectsCommonService.setCompliteTaskPersents(rootProductElement.getId(), 100);
		data = service.getCurrentTasks(null);
		rootProductElement = getElement(data);
		Assert.assertEquals(100, rootProductElement.getComplite());

		projectsCommonService.setCompliteTaskPersents(rootProductElement.getId(), 0);
		data = service.getCurrentTasks(null);
		rootProductElement = getElement(data);
		Assert.assertEquals(0, rootProductElement.getComplite());

		projectsCommonService.setCompliteTaskPersents(rootProductElement.getId(), 45);
		data = service.getCurrentTasks(null);
		rootProductElement = getElement(data);
		Assert.assertEquals(45, rootProductElement.getComplite());

		List<HistoryProxy> histories = commonService.getHistoryByObject(rootProductElement.getId());
		Assert.assertEquals(3, histories.size());

		Assert.assertEquals("Проставлен факт", histories.get(0).getName());
		Assert.assertEquals("D-001002 Покупка 1 было 0 стало 100", histories.get(0).getComment());
		Assert.assertEquals("D-001002 Покупка 1 было 100 стало 0", histories.get(1).getComment());
		Assert.assertEquals("D-001002 Покупка 1 было 0 стало 45", histories.get(2).getComment());

	}

	private GraphData getElement(GanttData data) {
		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);
		GraphData rootProductElement = rootProduct.getChilds().get(0);
		return rootProductElement;

	}

}
