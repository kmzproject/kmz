package ru.kmz.web.calendar.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;
import ru.kmz.web.calendar.client.CalendarModuleService;
import ru.kmz.web.calendar.shared.CalendarProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CalendarModuleServiceImpl extends RemoteServiceServlet implements CalendarModuleService {
	@Override
	public CalendarProxy getCalendar() {
		Calendar calendar = CalendarDataUtils.getCalendar();
		CalendarProxy proxy = calendar.asProxy();
		return proxy;
	}

	@Override
	public List<CalendarRecordProxy> getCalendarRecords() {
		Calendar calendar = CalendarDataUtils.getCalendar();
		List<CalendarRecord> list = CalendarDataUtils.getRecords(calendar.getKey());
		List<CalendarRecordProxy> result = new ArrayList<CalendarRecordProxy>();
		for (CalendarRecord record : list) {
			result.add(record.asProxy());
		}
		return result;
	}
}
