package ru.kmz.server.utils;

import java.util.Date;

import ru.kmz.server.data.model.History;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.HistoryDataUtils;

import com.google.appengine.api.datastore.Key;

public class HistoryUtils {

	private static interface HistoryName {
		String SET_FACT = "Проставлен факт";
		String CREATE_ORDER = "Создан заказ";
		String EDIT_ORDER = "Изменен заказ";
		String CREATE_PRODUCT = "Добавлено в производство";
		String DELETE_PRODUCT = "Удалено из производства";
	}

	public static History getDeleteProductElementTask(ProductElementTask task) {
		return getHistory(task.getKey(), HistoryName.DELETE_PRODUCT, task.toString() + " удалено из производства");
	}

	public static void addProductToOrder(ProductElementTask task) {
		createHistory(task.getKey(), HistoryName.CREATE_PRODUCT, task.toString() + " добавлено в производство");
	}

	public static void setFact(ProductElementTask task, int oldFact) {
		createHistory(task.getKey(), HistoryName.SET_FACT, task.toString() + " было " + oldFact + " стало " + task.getDone());
	}

	public static void createOrder(Order order) {
		createHistory(order.getKey(), HistoryName.CREATE_ORDER, order.toString());
	}

	public static void editOrder(Order order) {
		createHistory(order.getKey(), HistoryName.EDIT_ORDER, order.toString());
	}

	private static History getHistory(Key key, String name, String comment) {
		History history = new History();
		history.setDate(new Date());
		history.setObjectId(key);
		history.setComment(comment);
		history.setName(name);

		return history;
	}

	private static void createHistory(Key key, String name, String comment) {
		HistoryDataUtils.edit(getHistory(key, name, comment));
	}
}
