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

	public static void deleteProduct(String key) {
		PersistenceManager pm = null;
		ProductElementTask task;
		try {
			pm = PMF.get().getPersistenceManager();
			task = pm.getObjectById(ProductElementTask.class, key);
			pm.deletePersistent(task);
		} finally {
			pm.close();
		}
	}

	public static ProductElementTask getTaskFull(String key) {
		PersistenceManager pm = null;
		ProductElementTask task;
		try {
			pm = PMF.get().getPersistenceManager();
			task = pm.getObjectById(ProductElementTask.class, key);
			loadAllChild(pm, task);
		} finally {
			pm.close();
		}
		return task;
	}

	@SuppressWarnings("unchecked")
	public static void loadAllChild(PersistenceManager pm, ProductElementTask task) {
		Query query = pm.newQuery(ProductElementTask.class, " parentId == :parentKey");
		query.setOrdering("orderNum");
		List<ProductElementTask> list = (List<ProductElementTask>) query.execute(task.getKey());
		for (ProductElementTask e : list) {
			task.add(e);
			loadAllChild(pm, e);
		}
	}

	public static ProductElementTask edit(ProductElementTask task) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(task);
		} finally {
			pm.close();
		}
		return task;
	}

	public static ProductElementTask editFull(ProductElementTask task) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			editAllChild(pm, task);
		} finally {
			pm.close();
		}
		return task;
	}

	public static void editAllChild(PersistenceManager pm, ProductElementTask task) {
		if (task.hasChild()) {
			for (ProductElementTask e : task.getChilds()) {
				pm.makePersistent(e);
				editAllChild(pm, e);
			}
		}
	}

}
