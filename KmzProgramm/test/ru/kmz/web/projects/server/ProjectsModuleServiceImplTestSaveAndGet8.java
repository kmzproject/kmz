package ru.kmz.web.projects.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.gantt.client.config.GanttConfig.TaskType;

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

public class ProjectsModuleServiceImplTestSaveAndGet8 extends DataTestEveryNew {

	@Test
	public void getGantResultDataTest1Resrource() {
		Template template = TemplateTestData.createTemplateShort8();
		Order order = OrderTestData.createOrders1().get(0);
		ProjectsModuleServiceImpl service = new ProjectsModuleServiceImpl();
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		input.setOrderId(order.getKeyStr());
		input.setCount(2);
		service.save(input);

		GanttData data = service.getGantResultData(input);

		Assert.assertEquals(2, data.getChilds().size());
		GraphData rootTemplate = data.getChilds().get(0);
		GraphData rootOrder = data.getChilds().get(1);

		Assert.assertEquals("Тестовый заказ 1 (Z-001)", rootOrder.getName());
		Assert.assertEquals(ResourceTypes.ORDER, rootOrder.getResourceType());
		Assert.assertEquals(TaskType.PARENT, rootOrder.getTaskType());
		Assert.assertEquals("Изделие", rootTemplate.getName());
		Assert.assertEquals(ResourceTypes.PRODUCT, rootTemplate.getResourceType());
		Assert.assertEquals(TaskType.PARENT, rootTemplate.getTaskType());

		GraphData rootOrderProduct = rootOrder.getChilds().get(0);
		Assert.assertEquals("2x Изделие (I-001001)", rootOrderProduct.getName());
		Assert.assertEquals(ResourceTypes.PRODUCT, rootOrderProduct.getResourceType());

		Assert.assertEquals(DateUtils.getOffsetDate(date, -14), rootOrderProduct.getPlanStart());
		Assert.assertEquals(date, rootOrderProduct.getPlanFinish());

		Assert.assertEquals(DateUtils.getOffsetDate(date, -14), rootTemplate.getChilds().get(0).getPlanStart());
		Assert.assertEquals(date, rootTemplate.getChilds().get(0).getPlanFinish());
	}

}
