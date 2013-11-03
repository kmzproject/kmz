package ru.kmz.web.calendar.server;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.web.calendar.shared.CalendarProxy;
import ru.test.DataTestEveryNew;

public class GetCalendarTest extends DataTestEveryNew {

	private CalendarModuleServiceImpl service;

	@Before
	public void createService() {
		service = new CalendarModuleServiceImpl();
	}

	@Test
	public void getDateTest1() {

		CalendarTestData.craeteCalendar1();

		CalendarProxy calendar = service.getCalendar();
		Assert.assertEquals("Календарь", calendar.getName());

	}

}
