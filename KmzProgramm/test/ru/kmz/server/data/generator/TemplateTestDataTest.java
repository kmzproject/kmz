package ru.kmz.server.data.generator;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TemplateTestDataTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testCreateTemplateShort2() {
		Template templateSrc = TemplateTestData.createTemplateShort2();
		Assert.assertNotNull(templateSrc.getRootElement().getName());

		List<Template> templates = TemplateDataUtils.getAllTemplates();
		Assert.assertEquals(templates.size(), 1);
		Template template = templates.get(0);
		template = TemplateDataUtils.getTemplate(template.getKey().getId());

		ProducteTemplateElement rootElement = template.getRootElement();
		String rootElementKey = KeyFactory.keyToString(rootElement.getKey());
		rootElement = TemplateDataUtils.getProducteTemplateElement(rootElementKey);
		Assert.assertEquals(rootElement.getName(), "Изделие Shoet2");

		List<ProducteTemplateElement> rootChilds = rootElement.getChilds();
		Assert.assertEquals(rootChilds.size(), 1);

		ProducteTemplateElement element1 = rootChilds.get(0);
		String element1Key = KeyFactory.keyToString(element1.getKey());
		element1 = TemplateDataUtils.getProducteTemplateElement(element1Key);
		Assert.assertEquals(element1.getParent().getKey().compareTo(rootElement.getKey()), 0);

		List<ProducteTemplateElement> element1Childs = element1.getChilds();
		Assert.assertEquals(element1Childs.size(), 1);

		ProducteTemplateElement element1_1 = element1Childs.get(0);
		Assert.assertEquals(element1_1.getName(), "Вал");
	}
}
