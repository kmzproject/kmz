package ru.kmz.server.engine.calculator;

import java.util.Calendar;
import java.util.Date;

import ru.kmz.server.utils.DateUtils;
import ru.kmz.server.utils.HistoryUtils;

import com.google.appengine.api.datastore.Key;

public class GenerateWeekendService {

	private CreateCalendarRecordService createService;
	private Date from, to;
	private Key calendarId;

	public GenerateWeekendService(Key calendarId, Date from, Date to) {
		this.calendarId = calendarId;
		createService = new CreateCalendarRecordService(calendarId);

		this.from = DateUtils.getDateNoTime(from);
		this.to = DateUtils.getDateNoTime(to);
	}

	public void calculate() {
		int iterations = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		do {
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
				createService.create(calendar.getTime());
			}
			iterations++;
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		} while (iterations < 1000 && calendar.getTime().before(to));
		HistoryUtils.createCalculateWeekends(calendarId, from, to);

	}
}
