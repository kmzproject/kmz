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
import ru.kmz.web.projects.shared.UpdateProjectElementTaskParams;
import ru.test.DataTestEveryNew;

public class UpdateDateProductTest extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;
	private Date projectFinishDate;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
		prepareExample();
	}

	private void prepareExample() {
		projectFinishDate = DateUtils.getDate("2013/10/01");
		Date projectStartDate = DateUtils.getDate("2013/09/28");

		Template template = TemplateTestData.createTemplateShort5();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(projectFinishDate);
		input.setTemplateId(template.getId());
		input.setByFinishDate(true);
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
	public void updateOnlyDateFinish() {
		Date newDateFinish = DateUtils.getDate("2013/10/15");
		Date newDateStart = DateUtils.getDate("2013/10/12");

		GanttData data = service.getCurrentTasks(null);
		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);

		UpdateProjectElementTaskParams params = new UpdateProjectElementTaskParams();
		params.setFinishDate(newDateFinish);
		service.updateDate(rootProduct.getId(), params);

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		rootProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals(newDateFinish, rootOrder.getPlanFinish());
		Assert.assertEquals(newDateStart, rootOrder.getPlanStart());
		Assert.assertEquals(newDateFinish, rootProduct.getPlanFinish());
		Assert.assertEquals(newDateStart, rootProduct.getPlanStart());

		newDateFinish = DateUtils.getDate("2013/09/15");
		newDateStart = DateUtils.getDate("2013/09/12");

		params = new UpdateProjectElementTaskParams();
		params.setFinishDate(newDateFinish);
		service.updateDate(rootProduct.getId(), params);

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		rootProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals(newDateFinish, rootOrder.getPlanFinish());
		Assert.assertEquals(newDateStart, rootOrder.getPlanStart());
		Assert.assertEquals(newDateFinish, rootProduct.getPlanFinish());
		Assert.assertEquals(newDateStart, rootProduct.getPlanStart());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateStartAndFinishDate() {
		Date newDateFinish = DateUtils.getDate("2013/10/15");
		Date newDateStart = DateUtils.getDate("2013/10/12");

		GanttData data = service.getCurrentTasks(null);
		GraphData rootOrder = data.getChilds().get(0);
		GraphData rootProduct = rootOrder.getChilds().get(0);

		UpdateProjectElementTaskParams params = new UpdateProjectElementTaskParams();
		params.setFinishDate(newDateFinish);
		params.setStartDate(newDateStart);
		service.updateDate(rootProduct.getId(), params);
	}

}
