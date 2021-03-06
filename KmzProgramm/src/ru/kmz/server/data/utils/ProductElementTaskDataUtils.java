package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.utils.DateUtils;

public class ProductElementTaskDataUtils {

	@SuppressWarnings("unchecked")
	public static List<ProductElementTask> getNotComplitedTask(String resourceType, Date from, Date to) {
		List<ProductElementTask> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(ProductElementTask.class, " resourceType == '" + resourceType + "' && done <100");
			list = (List<ProductElementTask>) q.execute();
			if (from != null || to != null) {
				List<ProductElementTask> result = new ArrayList<ProductElementTask>();
				if (from != null) {
					from = DateUtils.getDateNoTime(from);
				}
				if (to != null) {
					to = DateUtils.getDateNoTime(to);
				}

				for (ProductElementTask productElementTask : list) {
					if (from != null) {
						if (!productElementTask.getFinish().after(from)) {
							continue;
						}
					}
					if (to != null) {
						if (productElementTask.getStart().after(to)) {
							continue;
						}
					}
					result.add(productElementTask);
				}
				return result;
			} else {
				return list;
			}
		} finally {
			pm.close();
		}
	}

	public static ProductElementTask getTask(Long key) {
		PersistenceManager pm = null;
		ProductElementTask task;
		try {
			pm = PMF.get().getPersistenceManager();
			task = pm.getObjectById(ProductElementTask.class, key);
		} finally {
			pm.close();
		}
		return task;
	}

	public static void deleteProduct(Long key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProductElementTask task = pm.getObjectById(ProductElementTask.class, key);
			loadAllChild(pm, task);
			deleteAndDeleteChilds(pm, task);
		} finally {
			pm.close();
		}
	}

	private static void deleteAndDeleteChilds(PersistenceManager pm, ProductElementTask task) {
		if (task.hasChild()) {
			for (ProductElementTask child : task.getChilds()) {
				deleteAndDeleteChilds(pm, child);
			}
		}
		pm.deletePersistent(task);
	}

	public static ProductElementTask getTaskFull(Long key) {
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
		List<ProductElementTask> list = (List<ProductElementTask>) query.execute(task.getId());
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
				editAllChild(pm, e);
			}
		}
		pm.makePersistent(task);
	}

	@SuppressWarnings("unchecked")
	public static List<ProductElementTask> getTasksByDate(Date from, Date to, String resourceType) {
		List<ProductElementTask> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(ProductElementTask.class, " resourceType == '" + resourceType + "' ");
			list = (List<ProductElementTask>) query.execute(from);
		} finally {
			pm.close();
		}
		return list;
	}
}
