package ru.kmz.server.engine.calculator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;
import ru.kmz.server.utils.DateUtils;

import com.google.appengine.api.datastore.Key;

public class GenerateWeekendService {

	private Key calendaerId;
	private Date from, to;

	public GenerateWeekendService(Key calendarId, Date from, Date to) {
		this.calendaerId = calendarId;

		this.from = DateUtils.getDateNoTime(from);
		this.to = DateUtils.getDateNoTime(to);
	}

	public void calculate() {
		List<CalendarRecord> records = CalendarDataUtils.getRecords(calendaerId);
		int iterations = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		do {
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
				CalendarRecord cr = new CalendarRecord(calendaerId, calendar.getTime());
				boolean contains = false;
				for (CalendarRecord calendarRecord : records) {
					if (calendarRecord.equals(cr)) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					CalendarDataUtils.edit(cr);
				}
			}
			iterations++;
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		} while (iterations < 1000 && calendar.getTime().before(to));

	}
}
