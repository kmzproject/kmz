package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.generator.CalendarTestData;
import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.utils.HistoryUtils;

public class CalendarDataUtils {

	private static Calendar calendarCache;
	private static List<CalendarRecord> calendarRecordsCache;

	static void cleanCache() {
		calendarCache = null;
		calendarRecordsCache = null;
	}

	@SuppressWarnings("unchecked")
	public static Calendar getCalendar() {
		if (calendarCache == null) {
			List<Calendar> list = null;
			PersistenceManager em = null;
			try {
				em = PMF.get().getPersistenceManager();
				Query query = em.newQuery(Calendar.class);
				list = (List<Calendar>) query.execute();
			} finally {
				em.close();
			}
			if (list.size() == 0) {
				calendarCache = CalendarTestData.craeteCalendar1();
			} else {
				calendarCache = list.get(0);
			}
		}
		return calendarCache;
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
		if (calendarRecordsCache == null) {
			initCalendarRecordsCache();
		}
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(record);
			calendarRecordsCache.add(record);
		} finally {
			pm.close();
		}
		return record;
	}

	public static List<CalendarRecord> getAllRecords() {
		if (calendarRecordsCache == null) {
			initCalendarRecordsCache();
		}
		return calendarRecordsCache;
	}

	@SuppressWarnings("unchecked")
	private static void initCalendarRecordsCache() {
		Long calendarId = getCalendar().getId();
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
		calendarRecordsCache = new ArrayList<CalendarRecord>(list);
	}

	public static void delete(Long recordId) {
		PersistenceManager pm = null;
		CalendarRecord record;
		try {
			pm = PMF.get().getPersistenceManager();
			record = pm.getObjectById(CalendarRecord.class, recordId);
			HistoryUtils.addDeleteCalendarRecord(record);
			int recodrIndex = calendarRecordsCache.indexOf(record);
			pm.deletePersistent(record);
			calendarRecordsCache.remove(recodrIndex);
		} finally {
			pm.close();
		}
	}
}
