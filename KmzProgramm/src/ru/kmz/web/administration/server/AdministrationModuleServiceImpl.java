package ru.kmz.web.administration.server;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.ResourceTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.web.administration.client.AdministrationModuleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AdministrationModuleServiceImpl extends RemoteServiceServlet implements AdministrationModuleService {

	@Override
	public void recreateData() {
		TemplateTestData.createDemoTemplates();
		OrderTestData.createOrders2();
		ResourceTestData.createReource1();
	}
}
