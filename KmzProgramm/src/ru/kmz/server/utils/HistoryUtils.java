package ru.kmz.server.utils;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.model.History;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.HistoryDataUtils;
import ru.kmz.server.data.utils.TemplateDataUtils;

public class HistoryUtils {

	private static interface HistoryName {
		String SET_FACT = "Проставлен факт";
		String ORDER_CREATE = "Создан заказ";
		String ORDER_EDITE = "Изменен заказ";
		String ORDER_DELETE = "Удален заказ";

		String CREATE_PRODUCT = "Добавлено в производство";
		String DELETE_PRODUCT = "Удалено из производства";
		String CALCULATE_CALENDAR = "Расчет выходных дней";
		String CREATE_CALENDAR_RECORD = "Добавлен выходной день";
		String DELETE_CALENDAR_RECORD = "Удален выходной";

		String TEMPLATE_CREATE = "Создан шаблон";
		String TEMPLATE_EDIT = "Внесены изменения в шаблон";
		String TEMPLATE_DELETE = "Шаблон удален";

		String TASK_STARTED = "Работа началась";
		String TASK_PLANNED = "Работа запланирована";

		String PRODUCT_TASK_CHANGE_FINISH_DATE = "Изменено время завершения заказа";
	}

	public static void createChangeProductFinishDate(ProductElementTask task, Date oldFinishDate) {
		createHistory(
				task.getId(),
				HistoryName.PRODUCT_TASK_CHANGE_FINISH_DATE,
				task.toString() + " преждняя дата окончания " + DateUtils.dateToString(oldFinishDate) + " ноавя дата окончания "
						+ DateUtils.dateToString(task.getFinish()));
	}

	public static void createTaskStarted(ProductElementTask task) {
		createHistory(task.getId(), HistoryName.TASK_STARTED, task.toString() + " работа началась");
	}

	public static void createTaskPlanned(ProductElementTask task) {
		createHistory(task.getId(), HistoryName.TASK_PLANNED, task.toString() + " работа запланирована");
	}

	public static void editTemplate(Long templateId) {
		Template template = TemplateDataUtils.getTemplate(templateId);
		editTemplate(template);
	}

	public static void editTemplate(Template template) {
		createHistory(template.getId(), HistoryName.TEMPLATE_EDIT, template.toString());
	}

	public static void addDeleteTemplate(Template template) {
		createHistory(template.getId(), HistoryName.TEMPLATE_DELETE, template.toString());
	}

	public static void addDeleteOrder(Order order) {
		createHistory(order.getId(), HistoryName.ORDER_DELETE, order.toString());
	}

	public static void createTemplate(Template template) {
		createHistory(template.getId(), HistoryName.TEMPLATE_CREATE, template.toString());
	}

	public static void createCalendarRecocd(CalendarRecord record) {
		createHistory(record.getCalendarId(), HistoryName.CREATE_CALENDAR_RECORD, record.toString());
	}

	public static void createCalculateWeekends(long calendarId, Date from, Date to) {
		createHistory(calendarId, HistoryName.CALCULATE_CALENDAR,
				"Автоматический расчет выходных дней с " + DateUtils.dateToString(from) + "  по " + DateUtils.dateToString(to));
	}

	public static void addDeleteCalendarRecord(CalendarRecord cr) {
		createHistory(cr.getCalendarId(), HistoryName.DELETE_CALENDAR_RECORD, cr.toString());
	}

	public static void addDeleteProductElementTask(ProductElementTask task) {
		createHistory(task.getId(), HistoryName.DELETE_PRODUCT, task.toString() + " удалено из производства");
	}

	public static void addProductToOrder(ProductElementTask task) {
		createHistory(task.getId(), HistoryName.CREATE_PRODUCT, task.toString() + " добавлено в производство");
	}

	public static void setFact(ProductElementTask task, int oldFact) {
		createHistory(task.getId(), HistoryName.SET_FACT, task.toString() + " было " + oldFact + " стало " + task.getDone());
	}

	public static void createOrder(Order order) {
		createHistory(order.getId(), HistoryName.ORDER_CREATE, order.toString());
	}

	public static void editOrder(Order order) {
		createHistory(order.getId(), HistoryName.ORDER_EDITE, order.toString());
	}

	private static History getHistory(long key, String name, String comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName;
		if (auth == null) {
			userName = "Nemo";
		} else {
//			User user = (User) auth.getPrincipal();
//			userName = user.getUsername();
			userName = (String) auth.getPrincipal();

		}
		History history = new History();
		history.setDate(new Date());
		history.setObjectId(key);
		history.setUser(userName);
		history.setComment(comment);
		history.setName(name);

		return history;
	}

	private static void createHistory(long key, String name, String comment) {
		HistoryDataUtils.edit(getHistory(key, name, comment));
	}
}
