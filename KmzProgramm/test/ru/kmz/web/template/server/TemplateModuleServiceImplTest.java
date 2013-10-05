package ru.kmz.web.template.server;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.test.DataTestEveryNew;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

public class TemplateModuleServiceImplTest extends DataTestEveryNew {

	@Test
	public void getDateTest() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateTreeDataProxy proxy = (new TemplateModuleServiceImpl()).getData(template.getKeyStr());
		Assert.assertEquals(proxy.getName(), template.getName());

		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertEquals(((TemplateTreeNodeFolderProxy) rootProxy.getChildren().get(0)).getChildren().get(0)
				.getName(), "Вал");
	}

	@Test
	public void createNewTemplateTreeNodeTest() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateModuleServiceImpl service = new TemplateModuleServiceImpl();
		TemplateTreeNodeBaseProxy newNodeProxy = service.createNewTemplateTreeNode(template.getRootElement()
				.getKeyStr());

		template = TemplateDataUtils.getTemplate(template.getKeyStr());
		Assert.assertEquals(template.getRootElement().getChilds().get(1).getName(), "Новый узел");
		Assert.assertEquals(template.getRootElement().getChilds().get(1).getKeyStr(), newNodeProxy.getId());
	}

	@Test
	public void saveTest() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateModuleServiceImpl service = new TemplateModuleServiceImpl();

		TemplateTreeNodeBaseProxy sourceProxy = template.getRootElement().getChilds().get(0).asProxy();
		sourceProxy.setName("New Name Test1");
		sourceProxy.setDuration(123);
		sourceProxy.setResourceType("New Resource Type1");
		TemplateTreeNodeBaseProxy proxy = service.save(sourceProxy);

		template = TemplateDataUtils.getTemplate(template.getKeyStr());
		Assert.assertEquals(template.getRootElement().getChilds().get(0).getName(), "New Name Test1");
		Assert.assertEquals(template.getRootElement().getChilds().get(0).getDuration(), 123);
		Assert.assertEquals(template.getRootElement().getChilds().get(0).getResourceType(), "New Resource Type1");

		Assert.assertEquals(template.getRootElement().getChilds().get(0).getKeyStr(), proxy.getId());
	}
}
