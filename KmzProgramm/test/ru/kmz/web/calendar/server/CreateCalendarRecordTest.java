package ru.kmz.web.calendar.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
import ru.test.DataTestEveryNew;

public class CreateCalendarRecordTest extends DataTestEveryNew {

	private CalendarModuleServiceImpl service;

	@Before
	public void createService() {
		service = new CalendarModuleServiceImpl();
	}

	@Test
	public void createRecordTest1() {

		CalendarTestData.craeteCalendar2();

		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(3, list.size());

		CalendarRecordProxy proxy = new CalendarRecordProxy();
		proxy.setDate(DateUtils.getDate("2013/11/09"));
		proxy.setComment("Еще один выходной");
		service.createCalendarRecord(proxy);
		list = service.getCalendarRecords();
		Assert.assertEquals(4, list.size());
	}

	@Test
	public void createRecordDoubleTest1() {

		CalendarTestData.craeteCalendar2();

		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(3, list.size());

		CalendarRecordProxy proxy = new CalendarRecordProxy();
		proxy.setDate(DateUtils.getDate("2013/11/09"));
		proxy.setComment("Еще один выходной");

		service.createCalendarRecord(proxy);
		service.createCalendarRecord(proxy);

		list = service.getCalendarRecords();
		Assert.assertEquals(4, list.size());
	}
}
