package ru.kmz.server.engine.calculation.resources;

import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.ResourceTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.test.DataTestEveryNew;

public class CalculateExecutaionServiceTest extends DataTestEveryNew {

	@Test
	public void test1() {
		Template template = TemplateTestData.createTemplateShort4();
		List<Resource> resources = ResourceTestData.createReource1();

		Date start = DateUtils.getDate("2013/10/01");
		CalculateExecutaionService service = new CalculateExecutaionService(start, resources);
		service.calculateElementFinish(template.getRootElement());
		Map<ProducteTemplateElement, ResourceTask> result = service.getResult();

		ProducteTemplateElement element1 = template.getRootElement().getChilds().get(0);
		Assert.assertEquals("Ходовая часть", element1.getName());
		ResourceTask task1 = result.get(element1);
		Assert.assertEquals("Test1", task1.getResource().getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), task1.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 6), task1.getFinish());
	}
}
