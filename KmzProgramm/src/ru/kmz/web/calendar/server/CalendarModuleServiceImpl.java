package ru.kmz.web.calendar.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.utils.CalendarDataUtils;
import ru.kmz.server.engine.calculator.CreateCalendarRecordService;
import ru.kmz.server.engine.calculator.GenerateWeekendService;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.web.calendar.client.CalendarModuleService;
import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.calendar.shared.CalendarProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;

@SuppressWarnings("serial")
public class CalendarModuleServiceImpl extends AbstractServiceImpl implements CalendarModuleService {
	@Override
	public CalendarProxy getCalendar() {
		Calendar calendar = CalendarDataUtils.getCalendar();
		CalendarProxy proxy = calendar.asProxy();
		return proxy;
	}

	@Override
	public List<CalendarRecordProxy> getCalendarRecords() {
		List<CalendarRecord> list = CalendarDataUtils.getAllRecords();
		List<CalendarRecordProxy> result = new ArrayList<CalendarRecordProxy>();
		for (CalendarRecord record : list) {
			result.add(record.asProxy());
		}
		return result;
	}

	@Override
	public void calculateWeekends(CalculateCalendarParamProxy params) {
		Calendar calendar = CalendarDataUtils.getCalendar();
		if (params.getFrom().before(params.getTo())) {
			GenerateWeekendService service = new GenerateWeekendService(calendar.getId(), params.getFrom(), params.getTo());
			service.calculate();
		} else {
			throw new IllegalArgumentException("Дата завершения не может быть меньше даты начала");
		}
	}

	@Override
	public void deleteCalendarRecord(long recordId) {
		CalendarDataUtils.delete(recordId);
	}

	@Override
	public void createCalendarRecord(CalendarRecordProxy proxy) {
		Calendar calendar = CalendarDataUtils.getCalendar();
		CreateCalendarRecordService service = new CreateCalendarRecordService(calendar.getId());
		service.create(proxy.getDate(), proxy.getComment());
	}

}
