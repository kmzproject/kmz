package ru.kmz.server.engine.calculator;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;
import ru.kmz.server.utils.HistoryUtils;

public class CreateCalendarRecordService {

	private long calendarId;
	private List<CalendarRecord> records;

	public CreateCalendarRecordService(long calendaeId) {
		this.calendarId = calendaeId;

		records = CalendarDataUtils.getAllRecords();
	}

	public void create(Date date) {
		create(date, null, false);
	}

	private void create(Date date, String comment, boolean history) {
		CalendarRecord cr = new CalendarRecord(calendarId, date, comment);
		boolean contains = false;
		for (CalendarRecord calendarRecord : records) {
			if (calendarRecord.equals(cr)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			cr = CalendarDataUtils.edit(cr);
			if (history) {
				HistoryUtils.createCalendarRecocd(cr);
			}
		}
	}

	public void create(Date date, String comment) {
		create(date, comment, true);
	}

}
