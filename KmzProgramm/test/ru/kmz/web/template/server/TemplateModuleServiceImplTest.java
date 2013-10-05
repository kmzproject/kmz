package ru.kmz.web.template.server;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Template;
import ru.kmz.test.DataTest;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

public class TemplateModuleServiceImplTest extends DataTest {

	@Test
	public void getDateTest() {
		Template template = TemplateTestData.createTemplateShort2();
		TemplateTreeDataProxy proxy = (new TemplateModuleServiceImpl()).getData(template.getKeyStr());
		Assert.assertEquals(proxy.getName(), template.getName());

		TemplateTreeNodeFolderProxy rootProxy = proxy.getTreeRoot();
		Assert.assertEquals(((TemplateTreeNodeFolderProxy) rootProxy.getChildren().get(0)).getChildren().get(0)
				.getName(), "Вал");
	}
}
