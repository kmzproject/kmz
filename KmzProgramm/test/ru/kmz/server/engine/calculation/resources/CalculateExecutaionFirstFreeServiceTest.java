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

public class CalculateExecutaionFirstFreeServiceTest extends DataTestEveryNew {

	@Test
	public void testNotResourcesTasks() {
		Template template = TemplateTestData.createTemplateShort5();
		List<Resource> resources = ResourceTestData.createReource1();

		Date start = DateUtils.getDate("2013/10/01");
		CalculateExecutaionFirstFreeService service = new CalculateExecutaionFirstFreeService(resources);
		service.calculateElementFinish(template.getRootElement(), start);
		Map<ProducteTemplateElement, ResourceTask> result = service.getResult();

		ProducteTemplateElement element1 = template.getRootElement().getChilds().get(0);
		Assert.assertEquals("Покупка 1", element1.getName());
		ResourceTask task1 = result.get(element1);
		Assert.assertNull(task1.getResource());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 2), task1.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), task1.getFinish());

		ProducteTemplateElement element2 = template.getRootElement().getChilds().get(1);
		Assert.assertEquals("Покупка 2", element2.getName());
		ResourceTask task2 = result.get(element2);
		Assert.assertNull(task2.getResource());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 1), task2.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), task2.getFinish());

		ProducteTemplateElement element3 = template.getRootElement().getChilds().get(2);
		Assert.assertEquals("Покупка 3", element3.getName());
		ResourceTask task3 = result.get(element3);
		Assert.assertNull(task3.getResource());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 0), task3.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), task3.getFinish());
	}

	@Test
	public void testSimplResourcesTasks() {
		Template template = TemplateTestData.createTemplateShort6();
		List<Resource> resources = ResourceTestData.createReource1();

		Date start = DateUtils.getDate("2013/10/01");
		CalculateExecutaionFirstFreeService service = new CalculateExecutaionFirstFreeService(resources);
		service.calculateElementFinish(template.getRootElement(), start);
		Map<ProducteTemplateElement, ResourceTask> result = service.getResult();

		ProducteTemplateElement element1 = template.getRootElement().getChilds().get(0);
		Assert.assertEquals("Сборка 1", element1.getName());
		ResourceTask task1 = result.get(element1);
		Assert.assertEquals("Test1", task1.getResource().getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 0), task1.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 1), task1.getFinish());

		ProducteTemplateElement element2 = template.getRootElement().getChilds().get(1);
		Assert.assertEquals("Сборка 2", element2.getName());
		ResourceTask task2 = result.get(element2);
		Assert.assertEquals("Test1", task2.getResource().getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 1), task2.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), task2.getFinish());

		ProducteTemplateElement element3 = template.getRootElement().getChilds().get(2);
		Assert.assertEquals("Сборка 3", element3.getName());
		ResourceTask task3 = result.get(element3);
		Assert.assertEquals("Test1", task3.getResource().getName());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3), task3.getStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 6), task3.getFinish());
	}

	// @Test
	// public void test1() {
	// Template template = TemplateTestData.createTemplateShort4();
	// List<Resource> resources = ResourceTestData.createReource1();
	//
	// Date start = DateUtils.getDate("2013/10/01");
	// CalculateExecutaionService service = new
	// CalculateExecutaionService(resources);
	// service.calculateElementFinish(template.getRootElement(), start);
	// Map<ProducteTemplateElement, ResourceTask> result = service.getResult();
	//
	// ProducteTemplateElement element1 =
	// template.getRootElement().getChilds().get(0);
	// Assert.assertEquals("Ходовая часть", element1.getName());
	// ResourceTask task1 = result.get(element1);
	// Assert.assertEquals("Test1", task1.getResource().getName());
	// Assert.assertEquals(CalculationUtils.getOffsetDate(start, 3),
	// task1.getStart());
	// Assert.assertEquals(CalculationUtils.getOffsetDate(start, 6),
	// task1.getFinish());
	// }
}
