package ru.kmz.server.data.generator;

import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.utils.CalendarDataUtils;

public class CalendarTestData {

	public static Calendar craeteCalendar1() {
		Calendar calendar = CalendarDataUtils.edit(new Calendar("Календарь"));
		return calendar;
	}
}
