package ru.kmz.server.data.generator;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.utils.OrderDataUtils;

public class OrderTestData {

	public static List<Order> createOrders1() {
		List<Order> list = new ArrayList<Order>();
		list.add(OrderDataUtils.edit(new Order("Тестовый заказ 1", "Заказчик Тест1", "Договор №1")));
		return list;
	}

	public static List<Order> createOrders2() {
		List<Order> list = new ArrayList<Order>();
		list.add(OrderDataUtils.edit(new Order("Гражданская", "", "")));
		list.add(OrderDataUtils.edit(new Order("Первомайка", "", "")));
		list.add(OrderDataUtils.edit(new Order("Рязанская ГРЭС", "", "")));
		list.add(OrderDataUtils.edit(new Order("Березнинки", "", "")));
		return list;
	}

	public static List<Order> createOrders3() {
		List<Order> list = new ArrayList<Order>();
		list.add(OrderDataUtils.edit(new Order("Тестовый заказ 1", "Заказчик Тест1", "Договор №1")));
		list.add(OrderDataUtils.edit(new Order("Тестовый заказ 2", "Заказчик Тест2", "Договор №2")));
		list.add(OrderDataUtils.edit(new Order("Тестовый заказ 3", "Заказчик Тест3", "Договор №3")));
		return list;
	}

}
