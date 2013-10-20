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
			Query q = pm.newQuery(ProductElementTask.class, " resourceType == '" + ResourceTypes.ORDER + "' && done <100");
			list = (List<ProductElementTask>) q.execute();
		} finally {
			pm.close();
		}
		return list;
	}

	public static ProductElementTask setTaskComplitePersents(String key, int persents) {
		PersistenceManager pm = null;
		ProductElementTask task;
		try {
			pm = PMF.get().getPersistenceManager();
			task = pm.getObjectById(ProductElementTask.class, key);
			task.setDone(persents);
			pm.makePersistent(task);
		} finally {
			pm.close();
		}
		return task;
	}
}
