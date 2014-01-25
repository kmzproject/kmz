package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;

public class OrderDataUtils {

	@SuppressWarnings("unchecked")
	public static List<Order> getAllOrders() {
		List<Order> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(Order.class);
			query.setOrdering("code");
			list = (List<Order>) query.execute();
		} finally {
			em.close();
		}
		return list;

	}

	public static Order edit(Order order) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			if (order.getId() == null) {
				int number = getMaxOrderNumber(pm);
				number++;
				order.setNumber(number);
			}
			pm.makePersistent(order);
		} finally {
			pm.close();
		}
		return order;
	}

	@SuppressWarnings("unchecked")
	private static int getMaxOrderNumber(PersistenceManager pm) {
		Query q = pm.newQuery(Order.class);
		q.setOrdering("number DESC");
		q.setRange(0, 1);
		List<Order> list = (List<Order>) q.execute();
		if (list.size() == 0)
			return 0;
		else {
			return list.get(0).getNumber();
		}
	}

	public static Order getOrder(Long key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Order order = pm.getObjectById(Order.class, key);
			loadAllChild(pm, order);
			return order;
		} finally {
			pm.close();
		}
	}

	public static void delete(Long key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Order order = pm.getObjectById(Order.class, key);
			pm.deletePersistent(order);
		} finally {
			pm.close();
		}
	}

	public static void loadOrder(Order order) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			loadAllChild(pm, order);
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadAllChild(PersistenceManager pm, Order order) {
		Query q = pm.newQuery(ProductElementTask.class, " parentId == null && orderId == :orderKey");
		List<ProductElementTask> list = (List<ProductElementTask>) q.execute(order.getId());
		for (ProductElementTask element : list) {
			order.add(element);
			ProductElementTaskDataUtils.loadAllChild(pm, element);
		}
	}
}