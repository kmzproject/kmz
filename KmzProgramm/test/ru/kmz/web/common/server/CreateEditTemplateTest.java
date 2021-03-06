package ru.kmz.web.common.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Template;
import ru.kmz.web.common.server.TemplateCommonServiceImpl;
import ru.kmz.web.common.shared.TemplateTreeDataProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeFolderProxy;
import ru.kmz.web.template.client.TemplateModuleService;
import ru.test.DataTestEveryNew;

public class CreateEditTemplateTest extends DataTestEveryNew {

	private TemplateModuleService service;

	@Before
	public void createService() {
		service = new TemplateCommonServiceImpl();
	}

	@Test
	public void editTemplate() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateTreeDataProxy proxy = service.getData(template.getId());
		Assert.assertEquals("Demo Template Short2", proxy.getName());

		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertEquals("Изделие Shoet2", rootProxy.getName());

		proxy.setName("NewTemplateName");
		service.editTemplate(proxy);
		proxy = service.getData(proxy.getId());

		Assert.assertEquals("NewTemplateName", proxy.getName());
		rootProxy = proxy.getTreeRoot();
		Assert.assertEquals("NewTemplateName", rootProxy.getName());
	}

	@Test
	public void createTemplate() {

		TemplateTreeDataProxy proxy = new TemplateTreeDataProxy();
		proxy.setName("NewTemplate");
		service.editTemplate(proxy);
		proxy = service.getTemplateList().get(0);
		proxy = service.getData(proxy.getId());

		Assert.assertEquals("NewTemplate", proxy.getName());
		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertNotNull(rootProxy);
		Assert.assertEquals("NewTemplate", rootProxy.getName());
	}

}
