package ru.kmz.server.data.generator;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.test.DataTest;

public class TemplateTestDataTest extends DataTest {

	@Test
	public void testCreateTemplateShort2() {
		Template template = TemplateTestData.createTemplateShort2();

		template = TemplateDataUtils.getTemplate(template.getId());

		ProductTemplateElement rootElement = template.getRootElement();
		Assert.assertNotNull(rootElement);
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		Assert.assertEquals(rootElement.getChilds().get(0).getName(), "Ходовая часть");
		Assert.assertEquals(rootElement.getChilds().get(0).getChilds().get(0).getName(), "Вал");

		long rootElementId = rootElement.getId();
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementId);
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		List<ProductTemplateElement> rootChilds = rootElement.getChilds();
		Assert.assertEquals(rootChilds.size(), 1);

		ProductTemplateElement element1 = rootChilds.get(0);
		long element1Id = element1.getId();
		element1 = TemplateDataUtils.getProducteTemplateElement(element1Id);
		Assert.assertEquals(element1.getParentId().compareTo(rootElement.getId()), 0);

		List<ProductTemplateElement> element1Childs = element1.getChilds();
		Assert.assertEquals(element1Childs.size(), 1);

		ProductTemplateElement element1_1 = element1Childs.get(0);
		Assert.assertEquals(element1_1.getName(), "Вал");
	}

	@Test
	public void testCreateTempate() {
		Template template = TemplateTestData.createTemplate1();
		Assert.assertEquals(template.getName(), "Demo Template 1");

		ProductTemplateElement rootElement = template.getRootElement();
		long rootElementId = rootElement.getId();
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementId);
		Assert.assertEquals(rootElement.getName(), "Изделие");

		ProductTemplateElement element1_1_1_1 = rootElement.getChilds().get(0).getChilds().get(0).getChilds().get(0).getChilds().get(0);
		Assert.assertEquals(element1_1_1_1.getName(), "Труба");
		ProductTemplateElement element4_3 = rootElement.getChilds().get(3).getChilds().get(2);
		Assert.assertEquals(element4_3.getName(), "Оси");
	}

}
