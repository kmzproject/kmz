package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.History;

public class HistoryDataUtils {

	private static List<History> historyCache = null;

	static void cleanCache() {
		historyCache = null;
	}

	public static History edit(History history) {
		if (historyCache == null) {
			initHistoriesCache();
		}
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(history);
			historyCache.add(0, history);
		} finally {
			pm.close();
		}
		return history;
	}

	public static List<History> getLastHistories() {
		if (historyCache == null) {
			initHistoriesCache();
		}
		return historyCache;
	}

	@SuppressWarnings("unchecked")
	private static void initHistoriesCache() {
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
		historyCache = new ArrayList<History>(list);
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
