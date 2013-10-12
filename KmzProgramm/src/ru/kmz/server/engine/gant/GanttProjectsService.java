package ru.kmz.server.engine.gant;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.IProjectTask;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
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

		data.setDateStart(minMaxDate.getMinDate());
		data.setDateFinish(minMaxDate.getMaxDate());

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
			MinMaxDate orderDate = fill(data, order);
			date.set(orderDate);
		}
		return date;
	}

	private MinMaxDate fill(IGraphDataContainer rootGraphData, IProjectTask task) {
		GraphData graphData = task.asGraphDataProxy();
		rootGraphData.addChild(graphData);

		if (graphData.isFolder()) {
			MinMaxDate date = new MinMaxDate();
			if (task.hasChild()) {
				for (ProductElementTask e : task.getChilds()) {
					MinMaxDate childDate = fill(graphData, e);
					date.set(childDate);
				}
				graphData.setPlanStart(date.getMinDate());
				graphData.setPlanFinish(date.getMaxDate());
				graphData.setDuration(date.getDuration());
			}
			return date;

		} else {
			ProductElementTask element = (ProductElementTask) task;
			graphData.setPlanStart(new Date(element.getStart().getTime()));
			graphData.setPlanFinish(new Date(element.getFinish().getTime()));
			MinMaxDate date = new MinMaxDate(element);

			if (element.hasChild()) {
				for (ProductElementTask e : element.getChilds()) {
					MinMaxDate childDate = fill(graphData, e);
					date.set(childDate);
				}
			}
			return date;
		}
	}

}
