package ru.kmz.server.data.generator;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TemplateTestDataTest {

	private final static LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@BeforeClass
	public static void setUp() {
		helper.setUp();
	}

	@AfterClass
	public static void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testCreateTemplateShort2() {
		Template template = TemplateTestData.createTemplateShort2();

		template = TemplateDataUtils.getTemplate(KeyFactory.keyToString(template.getKey()));

		ProducteTemplateElement rootElement = template.getRootElement();
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		Assert.assertEquals(rootElement.getChilds().get(0).getName(), "Ходовая часть");
		Assert.assertEquals(rootElement.getChilds().get(0).getChilds().get(0).getName(), "Вал");

		String rootElementKey = KeyFactory.keyToString(rootElement.getKey());
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementKey);
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		List<ProducteTemplateElement> rootChilds = rootElement.getChilds();
		Assert.assertEquals(rootChilds.size(), 1);

		ProducteTemplateElement element1 = rootChilds.get(0);
		String element1Key = KeyFactory.keyToString(element1.getKey());
		element1 = TemplateDataUtils.getProducteTemplateElement(element1Key);
		Assert.assertEquals(element1.getParentKey().compareTo(rootElement.getKey()), 0);

		List<ProducteTemplateElement> element1Childs = element1.getChilds();
		Assert.assertEquals(element1Childs.size(), 1);

		ProducteTemplateElement element1_1 = element1Childs.get(0);
		Assert.assertEquals(element1_1.getName(), "Вал");
	}

	@Test
	public void testCreateTempate() {
		Template template = TemplateTestData.createTemplate();
		Assert.assertEquals(template.getName(), "Main Demo Template");

		ProducteTemplateElement rootElement = template.getRootElement();
		String rootElementKey = KeyFactory.keyToString(rootElement.getKey());
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementKey);
		Assert.assertEquals(rootElement.getName(), "Изделие");

		ProducteTemplateElement element1_1_1_1 = rootElement.getChilds().get(0).getChilds().get(0).getChilds().get(0)
				.getChilds().get(0);
		Assert.assertEquals(element1_1_1_1.getName(), "Труба");
	}

}
