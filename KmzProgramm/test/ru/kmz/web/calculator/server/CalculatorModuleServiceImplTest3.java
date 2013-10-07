package ru.kmz.web.calculator.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.ResourceTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.test.DataTestEveryNew;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class CalculatorModuleServiceImplTest3 extends DataTestEveryNew {

	@Test
	public void getGantResultData1Resource() {
		Template template = TemplateTestData.createTemplateShort3();
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
		GraphData d1 = root.getChilds().get(0);

		GraphData d1_1 = d1.getChilds().get(0);
		Assert.assertEquals("Вал часть 1", d1_1.getName());
		Assert.assertEquals(start, d1_1.getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 10), d1_1.getPlanFinish());

		Assert.assertEquals("Ходовая часть", d1.getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 10), d1.getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 13), d1.getPlanFinish());

		Assert.assertEquals("Изделие Short3", root.getName());
		Assert.assertEquals(start, root.getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 13), root.getPlanFinish());
		Assert.assertEquals(1, root.getChilds().size());

	}
}
