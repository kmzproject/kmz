package ru.kmz.server.engine.calculation.resources;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.ResourceTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.test.DataTestEveryNew;

public class ResourceMapGeneratorTest extends DataTestEveryNew {

	@Test
	public void test1() {
		Template template = TemplateTestData.createTemplateShort4();
		List<Resource> resources = ResourceTestData.createReource1();

		ResourceMapGenerator generator = new ResourceMapGenerator(resources, template.getRootElement().getChilds().get(0).getChilds());
		generator.generate();
		List<ResourceMap> map = generator.getResourceMaps();

		Assert.assertEquals(map.size(), 1);

		Map<ProductTemplateElement, Resource> m = map.get(0).getMap();
		for (ProductTemplateElement element : m.keySet()) {
			if (element.getName().equals("Вал часть 1")) {
				Assert.assertEquals(m.get(element).getName(), "Test1");
			} else if (element.getName().equals("Вал часть 2")) {
				Assert.assertEquals(m.get(element).getName(), "Test1");
			} else if (element.getName().equals("Вал часть 3")) {
				Assert.assertEquals(m.get(element).getName(), "Test1");
			} else {
				print(map);
				Assert.fail();
			}
		}

	}

	@Test
	public void test2() {
		Template template = TemplateTestData.createTemplateShort4();
		List<Resource> resources = ResourceTestData.createReource2();

		ResourceMapGenerator generator = new ResourceMapGenerator(resources, template.getRootElement().getChilds().get(0).getChilds());

		generator.generate();
		List<ResourceMap> map = generator.getResourceMaps();

		Assert.assertEquals(map.size(), 8);
	}

	@Test
	public void test3() {
		Template template = TemplateTestData.createTemplateShort3();
		List<Resource> resources = ResourceTestData.createReource2();

		ResourceMapGenerator generator = new ResourceMapGenerator(resources, template.getRootElement().getChilds().get(0).getChilds());
		generator.generate();
		List<ResourceMap> map = generator.getResourceMaps();

		print(map);
		Assert.assertEquals(map.size(), 2);

		Map<ProductTemplateElement, Resource> m = map.get(0).getMap();
		for (ProductTemplateElement element : m.keySet()) {
			if (element.getName().equals("Вал часть 1")) {
				Assert.assertEquals(m.get(element).getName(), "Test1");
			} else {
				print(map);
				Assert.fail();
			}
		}

		m = map.get(1).getMap();
		for (ProductTemplateElement element : m.keySet()) {
			if (element.getName().equals("Вал часть 1")) {
				Assert.assertEquals(m.get(element).getName(), "Test2");
			} else {
				print(map);
				Assert.fail();
			}
		}

	}

	private static void print(List<ResourceMap> map) {
		for (ResourceMap resourceMap : map) {
			System.out.println("---------");
			resourceMap.print();
			System.out.println("---------");
		}
	}
}
