package ru.kmz.web.calendar.client;

import java.util.List;

import ru.kmz.web.calendar.shared.CalendarProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalendarModuleServiceAsync {

	void getCalendar(AsyncCallback<CalendarProxy> callback);

	void getCalendarRecords(AsyncCallback<List<CalendarRecordProxy>> callback);
}
