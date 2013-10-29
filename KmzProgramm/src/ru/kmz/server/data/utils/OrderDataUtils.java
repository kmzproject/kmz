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
			list = (List<Order>) query.execute();
		} finally {
			em.close();
		}
		return list;

	}

	public static Order edit(Order order) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			em.makePersistent(order);
		} finally {
			em.close();
		}
		return order;
	}

	public static Order getOrder(String key) {
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
		List<ProductElementTask> list = (List<ProductElementTask>) q.execute(order.getKey());
		for (ProductElementTask element : list) {
			order.add(element);
			ProductElementTaskDataUtils.loadAllChild(pm, element);
		}
	}
}