package ru.kmz.web.template.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Template;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeFolderProxy;
import ru.test.DataTestEveryNew;

public class CreateEditTemplateTest extends DataTestEveryNew {

	private TemplateModuleServiceImpl service;

	@Before
	public void createService() {
		service = new TemplateModuleServiceImpl();
	}

	@Test
	public void getDateTest2() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateTreeDataProxy proxy = service.getData(template.getKeyStr());
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
}
