package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProductElementTask;

public class ProductElementTaskDataUtils {

	@SuppressWarnings("unchecked")
	public static List<ProductElementTask> getNotComplitedOrders() {
		List<ProductElementTask> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(ProductElementTask.class, " resourceType == '" + ResourceTypes.ORDER + "' && done !=100");
			list = (List<ProductElementTask>) q.execute();
		} finally {
			pm.close();
		}
		return list;
	}

	public static void compliteTask(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProductElementTask task = pm.getObjectById(ProductElementTask.class, key);
			task.setDone(100);
			pm.makePersistent(task);
		} finally {
			pm.close();
		}

	}
}
