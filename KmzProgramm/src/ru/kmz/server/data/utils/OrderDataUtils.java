package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.model.Order;

public class OrderDataUtils {

	@SuppressWarnings("unchecked")
	public static List<Order> getAllOrders() {
		List<Order> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(Order.class);
			list = (List<Order>) query.execute();
			if (list.size() == 0) {
				return OrderTestData.createOrders1();
			}
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

}
