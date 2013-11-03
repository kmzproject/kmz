package ru.kmz.server.data.generator;

import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;
import ru.kmz.server.utils.DateUtils;

public class CalendarTestData {

	public static Calendar craeteCalendar1() {
		Calendar calendar = CalendarDataUtils.edit(new Calendar("Календарь"));
		CalendarDataUtils.edit(new CalendarRecord(calendar.getKey(), DateUtils.getDate("2013/11/02")));
		CalendarDataUtils.edit(new CalendarRecord(calendar.getKey(), DateUtils.getDate("2013/11/03")));
		CalendarDataUtils.edit(new CalendarRecord(calendar.getKey(), DateUtils.getDate("2013/11/04"), "День народного единства"));
		return calendar;
	}
}