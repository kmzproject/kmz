package ru.kmz.web.calendar.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.calendar.shared.CalendarProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
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

	@Test
	public void getDateTest2() {

		CalendarTestData.craeteCalendar1();

		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(3, list.size());

		Assert.assertEquals(DateUtils.getDate("2013/11/02"), list.get(0).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/03"), list.get(1).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/04"), list.get(2).getDate());
		Assert.assertEquals("День народного единства", list.get(2).getComment());
	}

}
