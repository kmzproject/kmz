package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.Calendar;

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

}
