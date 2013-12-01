package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.model.History;
import ru.kmz.server.utils.HistoryUtils;

public class CalendarDataUtils {

	@SuppressWarnings("unchecked")
	public static Calendar getCalendar() {
		List<Calendar> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(Calendar.class);
			list = (List<Calendar>) query.execute();
		} finally {
			em.close();
		}
		if (list.size() != 1) {
			return CalendarTestData.craeteCalendar1();
		}
		return list.get(0);
	}

	public static Calendar edit(Calendar calendar) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(calendar);
		} finally {
			pm.close();
		}
		return calendar;
	}

	public static CalendarRecord edit(CalendarRecord record) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(record);
		} finally {
			pm.close();
		}
		return record;
	}

//	@SuppressWarnings("unchecked")
//	public static List<CalendarRecord> getRecords(Long calendarId, Date limit) {
//		List<CalendarRecord> list = null;
//		PersistenceManager pm = null;
//		try {
//			pm = PMF.get().getPersistenceManager();
//			Query q = pm.newQuery(CalendarRecord.class, "this.date >= mydate && calendarId == calendarKey");
//			q.declareImports("import java.util.Date");
//			q.declareParameters("Date mydate, Long calendarKey");
//			System.out.println(q);
//			list = (List<CalendarRecord>) q.execute(limit, calendarId);
//		} finally {
//			pm.close();
//		}
//		return list;
//	}

	public static List<CalendarRecord> getAllRecords() {
		return getAllRecords(getCalendar().getId());
	}

	@SuppressWarnings("unchecked")
	public static List<CalendarRecord> getAllRecords(Long calendarId) {
		List<CalendarRecord> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(CalendarRecord.class, "calendarId == :calendarKey");
			q.setOrdering("date");
			list = (List<CalendarRecord>) q.execute(calendarId);
		} finally {
			pm.close();
		}
		return list;
	}

	public static void delete(Long recordId) {
		PersistenceManager pm = null;
		CalendarRecord record;
		try {
			pm = PMF.get().getPersistenceManager();
			record = pm.getObjectById(CalendarRecord.class, recordId);
			History history = HistoryUtils.getDeleteCalendarRecord(record);
			pm.deletePersistent(record);
			pm.makePersistent(history);
		} finally {
			pm.close();
		}
	}
}
