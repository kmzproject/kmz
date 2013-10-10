package ru.kmz.server.engine.calculation.resources;

import java.util.Date;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.test.DataTestEveryNew;

public class CalculateExecutionNoResourceServiceTest extends DataTestEveryNew {

	@Test
	public void testTemplateShort8() {
		Template template = TemplateTestData.createTemplateShort8();

		Date finish = DateUtils.getDate("2013/10/01");
		CalculateExecutionNoResourceService service = new CalculateExecutionNoResourceService();
		service.calculate(template, finish);
		Map<ProductTemplateElement, ResourceTask> result = service.getResult();

		ProductTemplateElement element1 = template.getRootElement().getChilds().get(0);
		Assert.assertEquals("Ходовая часть", element1.getName());
		ResourceTask task1 = result.get(element1);
		Assert.assertEquals(CalculationUtils.getOffsetDate(finish, -14), task1.getStart());
		Assert.assertEquals(finish, task1.getFinish());

		ProductTemplateElement element2 = template.getRootElement().getChilds().get(1);
		Assert.assertEquals("Рабочее колесо", element2.getName());
		ResourceTask task2 = result.get(element2);
		Assert.assertEquals(CalculationUtils.getOffsetDate(finish, -7), task2.getStart());
		Assert.assertEquals(finish, task2.getFinish());

		ProductTemplateElement element3 = template.getRootElement().getChilds().get(2);
		Assert.assertEquals("Корпуса", element3.getName());
		ResourceTask task3 = result.get(element3);
		Assert.assertEquals(CalculationUtils.getOffsetDate(finish, -14), task3.getStart());
		Assert.assertEquals(finish, task3.getFinish());

		ProductTemplateElement element4 = template.getRootElement().getChilds().get(3);
		Assert.assertEquals("Направляющий аппарат", element4.getName());
		ResourceTask task4 = result.get(element4);
		Assert.assertEquals(CalculationUtils.getOffsetDate(finish, -14), task4.getStart());
		Assert.assertEquals(finish, task4.getFinish());

	}
}
