package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.Calendar;
import ru.kmz.server.data.model.CalendarRecord;

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

	@SuppressWarnings("unchecked")
	public static List<CalendarRecord> getRecords(Key calendarId) {
		List<CalendarRecord> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(CalendarRecord.class, " calendarId == :calendarKey");
			list = (List<CalendarRecord>) q.execute(calendarId);
		} finally {
			pm.close();
		}
		return list;
	}

}
