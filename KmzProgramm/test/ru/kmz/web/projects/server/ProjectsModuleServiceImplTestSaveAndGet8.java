package ru.kmz.web.projects.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.test.DataTestEveryNew;

import com.gantt.client.config.GanttConfig.TaskType;

public class ProjectsModuleServiceImplTestSaveAndGet8 extends DataTestEveryNew {

	@Test
	public void getGantResultDataTest1Resrource() {
		Template template = TemplateTestData.createTemplateShort8();
		Order order = OrderTestData.createOrders1().get(0);
		ProjectsModuleServiceImpl service = new ProjectsModuleServiceImpl();
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getId());
		input.setOrderId(order.getId());
		input.setCount(2);
		service.save(input);
		GanttData data = service.getCurrentTasks(null);

		Assert.assertEquals(1, data.getChilds().size());
		GraphData rootOrder = data.getChilds().get(0);

		Assert.assertEquals("Тестовый заказ 1 (Z-001)", rootOrder.getName());
		Assert.assertEquals(ResourceTypes.ORDER, rootOrder.getResourceType());
		Assert.assertEquals(TaskType.PARENT, rootOrder.getTaskType());

		GraphData rootOrderProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals("Изделие [2] (I-001001)", rootOrderProduct.getName());
		Assert.assertEquals(ResourceTypes.PRODUCT, rootOrderProduct.getResourceType());

		Assert.assertEquals(DateUtils.getOffsetDate(date, -14), rootOrderProduct.getPlanStart());
		Assert.assertEquals(date, rootOrderProduct.getPlanFinish());
	}

	@Test
	public void getGantResultDataTest2() {
		Template template = TemplateTestData.createTemplateShort4();
		Order order = OrderTestData.createOrders1().get(0);
		ProjectsModuleServiceImpl service = new ProjectsModuleServiceImpl();
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getId());
		input.setOrderId(order.getId());
		service.save(input);
		GanttData data = service.getCurrentTasks(null);

		Assert.assertEquals(1, data.getChilds().size());
		GraphData rootOrder = data.getChilds().get(0);

		Assert.assertEquals(1, rootOrder.getChilds().size());

		Assert.assertEquals("Тестовый заказ 1 (Z-001)", rootOrder.getName());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -7), rootOrder.getPlanStart());
		Assert.assertEquals(date, rootOrder.getPlanFinish());

		GraphData rootOrderProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals("Изделие Short3 (I-001001)", rootOrderProduct.getName());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -7), rootOrderProduct.getPlanStart());
		Assert.assertEquals(date, rootOrderProduct.getPlanFinish());

		GraphData assembly = rootOrderProduct.getChilds().get(0);
		Assert.assertEquals("Ходовая часть (A-001002)", assembly.getName());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -4), assembly.getPlanStart());
		Assert.assertEquals(date, assembly.getPlanFinish());

		GraphData prepare1 = assembly.getChilds().get(0);
		Assert.assertEquals("Вал часть 1 (B-001003)", prepare1.getName());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -5), prepare1.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -4), prepare1.getPlanFinish());

		GraphData prepare2 = assembly.getChilds().get(1);
		Assert.assertEquals("Вал часть 2 (B-001004)", prepare2.getName());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -6), prepare2.getPlanStart());
		Assert.assertEquals(DateUtils.getOffsetDate(date, -4), prepare2.getPlanFinish());

		data = service.getCurrentTasks(null);
		rootOrder = data.getChilds().get(0);
		Assert.assertEquals(1, rootOrder.getChilds().size());

	}

}
