package ru.kmz.web.calendar.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
import ru.test.DataTestEveryNew;

public class DeleteCalendarRecordTest extends DataTestEveryNew {

	private CalendarModuleServiceImpl service;

	@Before
	public void createService() {
		service = new CalendarModuleServiceImpl();
	}

	@Test
	public void deleteTest1() {

		CalendarTestData.craeteCalendar2();

		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(3, list.size());
		service.deleteCalendarRecord(list.get(0).getId());
		list = service.getCalendarRecords();
		Assert.assertEquals(2, list.size());
	}
}
