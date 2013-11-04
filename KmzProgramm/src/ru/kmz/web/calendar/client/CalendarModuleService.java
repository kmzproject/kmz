package ru.kmz.web.calendar.client;

import java.util.List;

import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.calendar.shared.CalendarProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calendarModule")
public interface CalendarModuleService extends RemoteService {

	CalendarProxy getCalendar();

	List<CalendarRecordProxy> getCalendarRecords();

	void calculateWeekends(CalculateCalendarParamProxy params);

	void deleteCalendarRecord(String recordId);

	void createCalendarRecord(CalendarRecordProxy proxy);
}
