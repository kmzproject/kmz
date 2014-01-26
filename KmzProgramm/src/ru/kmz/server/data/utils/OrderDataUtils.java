package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;

public class OrderDataUtils {

	private static Map<Long, Order> orderCache = null;

	static void cleanCache() {
		orderCache = null;
	}

	public static List<Order> getAllOrders() {
		if (orderCache == null) {
			initOrderCache();
		}
		return new ArrayList<Order>(orderCache.values());
	}

	@SuppressWarnings("unchecked")
	private static void initOrderCache() {
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
		orderCache = new HashMap<Long, Order>();
		for (Order order : list) {
			orderCache.put(order.getId(), order);
		}
	}

	public static Order edit(Order order) {
		if (orderCache == null) {
			initOrderCache();
		}
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			if (order.getId() == null) {
				int number = getMaxOrderNumber(pm);
				number++;
				order.setNumber(number);
			}
			order = pm.makePersistent(order);
			orderCache.put(order.getId(), (Order) order.clone());
		} finally {
			pm.close();
		}
		return order;
	}

	@SuppressWarnings("unchecked")
	private static int getMaxOrderNumber(PersistenceManager pm) {
		// TODO: сделать использование кеша
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
		if (orderCache == null) {
			initOrderCache();
		}
		Order order = orderCache.get(key);
		return (Order) order.clone();
	}

	public static Order getOrderAndLoadAllChild(Long key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Order order = getOrder(key);
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
			orderCache.remove(order.getId());
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