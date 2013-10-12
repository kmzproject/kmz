package ru.kmz.web.templatecommon.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;
import ru.test.DataTestEveryNew;

public class TemplateCommonServiceImplTest extends DataTestEveryNew {

	private TemplateCommonServiceImpl service;

	@Before
	public void createService() {
		service = new TemplateCommonServiceImpl();
	}

	@Test
	public void getDateTest1() {

		TemplateTestData.createTemplate1();
		List<TemplateTreeDataProxy> list = service.getTemplateList();
		Assert.assertEquals(1, list.size());

	}

}
