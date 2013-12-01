package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.History;

public class HistoryDataUtils {

	public static History edit(History history) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(history);
		} finally {
			pm.close();
		}
		return history;
	}

	@SuppressWarnings("unchecked")
	public static List<History> getLastHistories() {
		List<History> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(History.class);
			query.setOrdering("date DESC");
			query.setRange(0, 50);
			list = (List<History>) query.execute();
		} finally {
			pm.close();
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public static List<History> getHistoriesByObject(Long key) {
		List<History> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(History.class, " objectId == :objectId");
			q.setOrdering("date");
			list = (List<History>) q.execute(key);
		} finally {
			pm.close();
		}
		return list;

	}

}
