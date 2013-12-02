package ru.kmz.server.engine.gant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.CalendarRecord;
import ru.kmz.server.data.model.IProjectTask;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.CalendarDataUtils;
import ru.kmz.server.engine.projects.GetOrdersService;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.ganttcommon.shared.IGraphDataContainer;

public class GanttProjectsService {

	protected GanttData data;
	private GetOrdersService ordersService;

	public GanttProjectsService() {
	}

	public GanttData getGantData() {
		return data;
	}

	public void setGetOrdersService(GetOrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public void calculate() {
		createGanttData();

		MinMaxDate minMaxDate = process();

		minMaxDate.prepare();
		data.setDateStart(minMaxDate.getMinDate());
		data.setDateFinish(minMaxDate.getMaxDate());

		addCalendarRecords();
	}

	private void addCalendarRecords() {
		List<Date> calendarRecords = new ArrayList<Date>();
		List<CalendarRecord> records = CalendarDataUtils.getAllRecords();
		for (CalendarRecord calendarRecord : records) {
			calendarRecords.add(new Date(calendarRecord.getDate().getTime()));
		}
		data.setCalendarRecords(calendarRecords);
	}

	protected MinMaxDate process() {
		MinMaxDate minMaxDate = addProjectData();
		return minMaxDate;
	}

	protected void createGanttData() {
		data = new GanttData("Производство");
	}

	private MinMaxDate addProjectData() {
		if (ordersService == null)
			return null;
		MinMaxDate date = new MinMaxDate();
		List<Order> orders = ordersService.getOrders();
		for (Order order : orders) {
			fill(data, order);
			date.set(new MinMaxDate(order));
		}
		sort();
		calculateOrdersPersentsDone();
		return date;
	}

	/** Сортирует только 2 верних уровня, заказы и изделия в заказе */
	private void sort() {
		Collections.sort(data.getChilds());
		for (GraphData graphData : data.getChilds()) {
			Collections.sort(graphData.getChilds());
		}
	}

	private void calculateOrdersPersentsDone() {
		for (GraphData orderData : data.getChilds()) {
			calculatePersentsDone(orderData);
		}
	}

	private DurationComplite calculatePersentsDone(GraphData graphData) {
		DurationComplite dc = new DurationComplite();
		for (GraphData child : graphData.getChilds()) {
			dc.add(calculatePersentsDone(child));
		}
		if (!graphData.isFolder()) {
			dc.add(new DurationComplite(graphData.getDuration(), graphData.getComplite()));
		} else {
			graphData.setComplite(dc.getPersent());
		}
		return dc;
	}

	private static void fill(IGraphDataContainer rootGraphData, IProjectTask task) {
		GraphData graphData = task.asGraphDataProxy();
		rootGraphData.addChild(graphData);

		graphData.setPlanStart(new Date(task.getStart().getTime()));
		graphData.setPlanFinish(new Date(task.getFinish().getTime()));

		if (task.hasChild()) {
			for (ProductElementTask e : task.getChilds()) {
				fill(graphData, e);
			}
		}
	}

}
