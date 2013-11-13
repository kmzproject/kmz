package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.History;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(History.class);
			list = (List<History>) query.execute();
		} finally {
			em.close();
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public static List<History> getHistoriesByObject(String keyStr) {
		Key key = KeyFactory.stringToKey(keyStr);
		List<History> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(History.class, " objectId == :objectId");
			list = (List<History>) q.execute(key);
		} finally {
			pm.close();
		}
		return list;

	}

}
