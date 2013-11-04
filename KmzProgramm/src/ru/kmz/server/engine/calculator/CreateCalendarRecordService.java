package ru.kmz.server.engine.calculator;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;

import com.google.appengine.api.datastore.Key;

public class CreateCalendarRecordService {

	private Key calendarId;
	private List<CalendarRecord> records;

	public CreateCalendarRecordService(Key calendaeId) {
		this.calendarId = calendaeId;

		records = CalendarDataUtils.getRecords(calendaeId);
	}

	public void create(Date date) {
		create(date, null);
	}

	public void create(Date date, String comment) {
		CalendarRecord cr = new CalendarRecord(calendarId, date, comment);
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

}
