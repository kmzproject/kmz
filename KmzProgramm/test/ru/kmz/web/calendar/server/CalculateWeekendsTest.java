package ru.kmz.web.calendar.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
import ru.test.DataTestEveryNew;

public class CalculateWeekendsTest extends DataTestEveryNew {

	private CalendarModuleServiceImpl service;

	@Before
	public void createService() {
		service = new CalendarModuleServiceImpl();
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateIllegalArgumentTest1() {
		CalendarTestData.craeteCalendar1();

		CalculateCalendarParamProxy params = new CalculateCalendarParamProxy(DateUtils.getDate("2013/11/01"), DateUtils.getDate("2013/11/01"));
		service.calculateWeekends(params);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateIllegalArgumentTest2() {
		CalendarTestData.craeteCalendar1();

		CalculateCalendarParamProxy params = new CalculateCalendarParamProxy(DateUtils.getDate("2013/11/01"), DateUtils.getDate("2013/10/01"));
		service.calculateWeekends(params);
	}

	@Test
	public void calculateTest1() {

		CalendarTestData.craeteCalendar1();

		CalculateCalendarParamProxy params = new CalculateCalendarParamProxy(DateUtils.getDate("2013/11/01"), DateUtils.getDate("2013/12/01"));

		service.calculateWeekends(params);
		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(9, list.size());

		Assert.assertEquals(DateUtils.getDate("2013/11/02"), list.get(0).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/03"), list.get(1).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/09"), list.get(2).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/10"), list.get(3).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/16"), list.get(4).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/17"), list.get(5).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/23"), list.get(6).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/24"), list.get(7).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/30"), list.get(8).getDate());
	}

	@Test
	public void calculateDoubleCalculeteTest2() {

		CalendarTestData.craeteCalendar1();

		CalculateCalendarParamProxy params = new CalculateCalendarParamProxy(DateUtils.getDate("2013/11/01"), DateUtils.getDate("2013/12/01"));

		service.calculateWeekends(params);
		service.calculateWeekends(params);
		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(9, list.size());

		Assert.assertEquals(DateUtils.getDate("2013/11/02"), list.get(0).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/03"), list.get(1).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/09"), list.get(2).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/10"), list.get(3).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/16"), list.get(4).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/17"), list.get(5).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/23"), list.get(6).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/24"), list.get(7).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/30"), list.get(8).getDate());
	}

	@Test
	public void calculateCalculeteTest3() {

		CalendarTestData.craeteCalendar2();

		CalculateCalendarParamProxy params = new CalculateCalendarParamProxy(DateUtils.getDate("2013/11/01"), DateUtils.getDate("2013/12/01"));

		service.calculateWeekends(params);
		service.calculateWeekends(params);
		List<CalendarRecordProxy> list = service.getCalendarRecords();
		Assert.assertEquals(10, list.size());

		Assert.assertEquals(DateUtils.getDate("2013/11/02"), list.get(0).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/03"), list.get(1).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/04"), list.get(2).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/09"), list.get(3).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/10"), list.get(4).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/16"), list.get(5).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/17"), list.get(6).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/23"), list.get(7).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/24"), list.get(8).getDate());
		Assert.assertEquals(DateUtils.getDate("2013/11/30"), list.get(9).getDate());
	}

}
