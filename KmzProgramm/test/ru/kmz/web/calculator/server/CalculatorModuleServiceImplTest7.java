package ru.kmz.web.calculator.server;

import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;
import ru.kmz.server.data.generator.ResourceTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.test.DataTestEveryNew;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class CalculatorModuleServiceImplTest7 extends DataTestEveryNew {

	@Test
	public void getGantResultDataTest1Resrource() {
		Template template = TemplateTestData.createTemplateShort7();
		ResourceTestData.createReource1();

		CalculatorModuleServiceImpl service = new CalculatorModuleServiceImpl();
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date start = DateUtils.getDate("2013/10/01");
		input.setDate(start);
		input.setTemplateId(template.getKeyStr());
		input.setByStartDate(true);
		input.setUseResource(true);
		GanttData data = service.getGantResultData(input);

		Assert.assertEquals(start, data.getDateStart());

		GraphData root = data.getChilds().get(0);

		GraphData d1_1 = root.getChilds().get(0).getChilds().get(0);
		Assert.assertEquals("Покупка 1", d1_1.getName());
		Assert.assertEquals(start, d1_1.getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), d1_1.getPlanFinish());

		GraphData d1_2 = root.getChilds().get(1).getChilds().get(0);
		Assert.assertEquals("Покупка 2", d1_2.getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), d1_2.getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 6), d1_2.getPlanFinish());

		GraphData d1_3 = root.getChilds().get(2).getChilds().get(0);
		Assert.assertEquals("Покупка 3", d1_3.getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 6), d1_3.getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 9), d1_3.getPlanFinish());
	}
}
