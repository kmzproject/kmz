package ru.kmz.web.common.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.web.common.shared.TemplateTreeDataProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeFolderProxy;
import ru.test.DataTestEveryNew;

public class TemplateModuleServiceImplTest extends DataTestEveryNew {

	private TemplateCommonServiceImpl service;

	@Before
	public void createService() {
		service = new TemplateCommonServiceImpl();
	}

	@Test
	public void getDateTest1() {
		Template template = TemplateTestData.createTemplate1();
		TemplateTreeDataProxy proxy = service.getData(template.getId());
		Assert.assertEquals(proxy.getName(), template.getName());

		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertEquals(rootProxy.getChildren().get(0).getName(), "Ходовая часть");
		Assert.assertEquals(rootProxy.getChildren().get(1).getName(), "Рабочее колесо");
		Assert.assertEquals(rootProxy.getChildren().get(2).getName(), "Корпуса");
		Assert.assertEquals(rootProxy.getChildren().get(3).getName(), "Н.А.");
	}

	@Test
	public void getDateTest2() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateTreeDataProxy proxy = service.getData(template.getId());
		Assert.assertEquals(proxy.getName(), template.getName());

		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertEquals(((TemplateTreeNodeFolderProxy) rootProxy.getChildren().get(0)).getChildren().get(0).getName(), "Вал");
	}

	@Test
	public void getDateTest4() {
		Template template = TemplateTestData.createTemplateShort4();
		TemplateTreeDataProxy proxy = service.getData(template.getId());
		Assert.assertEquals(proxy.getName(), template.getName());

		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertEquals(((TemplateTreeNodeFolderProxy) rootProxy.getChildren().get(0)).getChildren().get(0).getName(), "Вал часть 1");
	}

	@Test
	public void createNewTemplateTreeNodeTest() {
		Template template = TemplateTestData.createTemplateShort2();

		TemplateTreeNodeBaseProxy proxtSrc = new TemplateTreeNodeBaseProxy();
		proxtSrc.setDuration(13);
		proxtSrc.setName("Тестовый узел 1");
		proxtSrc.setResourceType(ResourceTypes.PRODUCT);
		TemplateTreeNodeBaseProxy newNodeProxy = service.createNewTemplateTreeNode(template.getRootElement().getId(), proxtSrc);

		template = TemplateDataUtils.getTemplate(template.getId());

		ProductTemplateElement element = template.getRootElement().getChilds().get(1);
		Assert.assertEquals(element.getName(), "Тестовый узел 1");
		Assert.assertEquals(element.getResourceType(), ResourceTypes.PRODUCT);
		Assert.assertEquals(element.getDuration(), 13);
		Assert.assertEquals(element.getId(), newNodeProxy.getId());
	}

	@Test
	public void saveTest() {
		Template template = TemplateTestData.createTemplateShort2();

		TemplateTreeNodeBaseProxy sourceProxy = template.getRootElement().getChilds().get(0).asProxy();
		sourceProxy.setName("New Name Test1");
		sourceProxy.setDuration(123);
		sourceProxy.setResourceType("New Resource Type1");
		TemplateTreeNodeBaseProxy proxy = service.save(sourceProxy);

		template = TemplateDataUtils.getTemplate(template.getId());
		Assert.assertEquals(template.getRootElement().getChilds().get(0).getName(), "New Name Test1");
		Assert.assertEquals(template.getRootElement().getChilds().get(0).getDuration(), 123);
		Assert.assertEquals(template.getRootElement().getChilds().get(0).getResourceType(), "New Resource Type1");

		Assert.assertEquals(template.getRootElement().getChilds().get(0).getId(), proxy.getId());
	}

	@Test
	public void deleteTest() {
		Template template = TemplateTestData.createTemplateShort2();

		service.deleteTemplateTreeNode(template.getRootElement().getChilds().get(0).getChilds().get(0).getId());

		template = TemplateDataUtils.getTemplate(template.getId());
		Assert.assertEquals(template.getRootElement().getChilds().get(0).hasChild(), false);
	}

	@Test
	public void copyTest() {
		Template template = TemplateTestData.createTemplateShort2();
		template = TemplateDataUtils.getTemplate(template.getId());

		ProductTemplateElement sourceElement = template.getRootElement().getChilds().get(0);

		Assert.assertEquals("Ходовая часть", sourceElement.getName());

		TemplateTreeDataProxy newTemplateProxy = service.copyTemplate(template.getId());
		Template newTemplate = TemplateDataUtils.getTemplate(newTemplateProxy.getId());

		ProductTemplateElement destRootElement = newTemplate.getRootElement();
		Assert.assertNotNull(destRootElement);
		Assert.assertNotNull(destRootElement.getChilds());
		ProductTemplateElement destElement = destRootElement.getChilds().get(0);
		Assert.assertEquals("Ходовая часть", destElement.getName());

	}

}
