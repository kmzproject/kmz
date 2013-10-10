package ru.kmz.server.data.generator;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.test.DataTest;

import com.google.appengine.api.datastore.KeyFactory;

public class TemplateTestDataTest extends DataTest {

	@Test
	public void testCreateTemplateShort2() {
		Template template = TemplateTestData.createTemplateShort2();

		template = TemplateDataUtils.getTemplate(KeyFactory.keyToString(template.getKey()));

		ProductTemplateElement rootElement = template.getRootElement();
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		Assert.assertEquals(rootElement.getChilds().get(0).getName(), "Ходовая часть");
		Assert.assertEquals(rootElement.getChilds().get(0).getChilds().get(0).getName(), "Вал");

		String rootElementKey = KeyFactory.keyToString(rootElement.getKey());
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementKey);
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		List<ProductTemplateElement> rootChilds = rootElement.getChilds();
		Assert.assertEquals(rootChilds.size(), 1);

		ProductTemplateElement element1 = rootChilds.get(0);
		String element1Key = KeyFactory.keyToString(element1.getKey());
		element1 = TemplateDataUtils.getProducteTemplateElement(element1Key);
		Assert.assertEquals(element1.getParentKey().compareTo(rootElement.getKey()), 0);

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
		String rootElementKey = KeyFactory.keyToString(rootElement.getKey());
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementKey);
		Assert.assertEquals(rootElement.getName(), "Изделие");

		ProductTemplateElement element1_1_1_1 = rootElement.getChilds().get(0).getChilds().get(0).getChilds().get(0)
				.getChilds().get(0);
		Assert.assertEquals(element1_1_1_1.getName(), "Труба");
		ProductTemplateElement element4_3 = rootElement.getChilds().get(3).getChilds().get(2);
		Assert.assertEquals(element4_3.getName(), "Оси");
	}

}
