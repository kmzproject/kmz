package ru.kmz.web.administration.server;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.ResourceTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.web.administration.client.AdministrationModuleService;

@SuppressWarnings("serial")
public class AdministrationModuleServiceImpl extends AbstractServiceImpl implements AdministrationModuleService {

	@Override
	public void recreateData() {
		TemplateTestData.createDemoTemplates();
		OrderTestData.createOrders2();
		ResourceTestData.createReource1();
		CalendarTestData.craeteCalendar1();
	}
}
