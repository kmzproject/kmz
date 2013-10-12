package ru.kmz.web.calculator.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.test.DataTestEveryNew;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class CalculatorModuleServiceImplTestSaveAndGet8 extends DataTestEveryNew {

	@Test
	public void getGantResultDataTest1Resrource() {
		Template template = TemplateTestData.createTemplateShort8();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorModuleServiceImpl service = new CalculatorModuleServiceImpl();
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		input.setByStartDate(true);
		input.setUseResource(false);
		input.setShowOtherTasks(true);
		String orderId = order.getKeyStr();
		service.save(input, orderId);

		GanttData data = service.getGantResultData(input);

		Assert.assertEquals(2, data.getChilds().size());
		GraphData rootTemplate = data.getChilds().get(0);
		GraphData rootOrder = data.getChilds().get(1);

		Assert.assertEquals("Тестовый заказ 1", rootOrder.getName());
		Assert.assertEquals("Изделие", rootTemplate.getName());

		Assert.assertEquals("Изделие", rootOrder.getChilds().get(0).getName());

		Assert.assertEquals(CalculationUtils.getOffsetDate(date, -14), rootOrder.getChilds().get(0).getPlanStart());
		Assert.assertEquals(date, rootOrder.getChilds().get(0).getPlanFinish());

		Assert.assertEquals(CalculationUtils.getOffsetDate(date, -14), rootTemplate.getChilds().get(0).getPlanStart());
		Assert.assertEquals(date, rootTemplate.getChilds().get(0).getPlanFinish());
	}

}
