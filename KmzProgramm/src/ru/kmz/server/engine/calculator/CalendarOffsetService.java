package ru.kmz.server.engine.calculator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;

public class CalendarOffsetService {

	private List<Date> list;
	private Calendar current;

	public CalendarOffsetService() {
		List<CalendarRecord> records = CalendarDataUtils.getAllRecords(CalendarDataUtils.getCalendar().getId());
		list = new ArrayList<Date>();
		for (CalendarRecord record : records) {
			list.add(record.getDate());
		}
		current = Calendar.getInstance();
	}

	public Date getOffsetDate(Date date, int days) {
		int d = days > 0 ? 1 : -1;
		current.setTime(date);
		while (days != 0) {
			current.add(Calendar.DAY_OF_YEAR, d);
			if (list.contains(current.getTime())) {
				continue;
			}
			days -= d;
		}
		return current.getTime();
	}
}
