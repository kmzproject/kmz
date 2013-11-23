package ru.kmz.server.utils;

import java.util.Date;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.model.History;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.HistoryDataUtils;
import ru.kmz.server.data.utils.TemplateDataUtils;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class HistoryUtils {

	private static interface HistoryName {
		String SET_FACT = "Проставлен факт";
		String CREATE_ORDER = "Создан заказ";
		String EDIT_ORDER = "Изменен заказ";
		String CREATE_PRODUCT = "Добавлено в производство";
		String DELETE_PRODUCT = "Удалено из производства";
		String CALCULATE_CALENDAR = "Расчет выходных дней";
		String CREATE_CALENDAR_RECORD = "Добавлен выходной день";
		String DELETE_CALENDAR_RECORD = "Удален выходной";

		String TEMPLATE_CREATE = "Создан шаблон";
		String TEMPLATE_EDIT = "Внесены изменения в шаблон";

		String TASK_STARTED = "Работа началась";
	}

	public static void createTaskStarted(ProductElementTask task) {
		createHistory(task.getKey(), HistoryName.TASK_STARTED, task.toString() + " работа началась");
	}

	public static void editTemplate(Key templateId) {
		Template template = TemplateDataUtils.getTemplate(KeyFactory.keyToString(templateId));
		editTemplate(template);
	}

	public static void editTemplate(Template template) {
		createHistory(template.getKey(), HistoryName.TEMPLATE_EDIT, template.toString());
	}

	public static void createTemplate(Template template) {
		createHistory(template.getKey(), HistoryName.TEMPLATE_CREATE, template.toString());
	}

	public static void createCalendarRecocd(CalendarRecord record) {
		createHistory(record.getCalendarId(), HistoryName.CREATE_CALENDAR_RECORD, record.toString());
	}

	public static void createCalculateWeekends(Key calendarId, Date from, Date to) {
		createHistory(calendarId, HistoryName.CALCULATE_CALENDAR,
				"Автоматический расчет выходных дней с " + DateUtils.dateToString(from) + "  по " + DateUtils.dateToString(to));
	}

	public static History getDeleteCalendarRecord(CalendarRecord cr) {
		return getHistory(cr.getCalendarId(), HistoryName.DELETE_CALENDAR_RECORD, cr.toString());
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
