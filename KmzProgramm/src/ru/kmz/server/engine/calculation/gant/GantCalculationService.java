package ru.kmz.server.engine.calculation.gant;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.IProjectTask;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.server.engine.calculation.project.GetOrdersService;
import ru.kmz.server.engine.calculation.resources.ICalcucateExecutionServiceInterface;
import ru.kmz.server.engine.calculation.resources.ResourceTask;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.ganttcommon.shared.IGraphDataContainer;

public class GantCalculationService {

	private GanttData data;
	private ICalcucateExecutionServiceInterface service;
	private GetOrdersService ordersService;

	public GantCalculationService() {
	}

	public GanttData getGantData() {
		return data;
	}

	public void setService(ICalcucateExecutionServiceInterface service) {
		this.service = service;
	}

	public void setGetOrdersService(GetOrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public GanttData calculate(Template template, Date date) {
		service.calculate(template, date);

		data = new GanttData("Расчет по шаблону " + template.getName());

		ProducteTemplateElement root = template.getRootElement();

		MinMaxDate minMaxDate = fill(data, root);

		MinMaxDate minMaxProjectDate = addProjectData();

		minMaxDate.set(minMaxProjectDate);

		data.setDateStart(minMaxDate.minDate);
		data.setDateFinish(minMaxDate.maxDate);

		return data;
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
				graphData.setPlanStart(date.minDate);
				graphData.setPlanFinish(date.maxDate);
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

	private MinMaxDate fill(IGraphDataContainer rootGraphData, ProducteTemplateElement element) {
		GraphData graphData = element.asGraphDataProxy();
		rootGraphData.addChild(graphData);

		if (graphData.isFolder()) {
			MinMaxDate date = new MinMaxDate();
			if (element.hasChild()) {
				for (ProducteTemplateElement e : element.getChilds()) {
					MinMaxDate childDate = fill(graphData, e);
					date.set(childDate);
				}
			}
			graphData.setPlanStart(date.minDate);
			graphData.setPlanFinish(date.maxDate);
			graphData.setDuration(date.getDuration());
			return date;

		} else {
			ResourceTask task = service.getResult().get(element);

			graphData.setPlanStart(task.getStart());
			graphData.setPlanFinish(task.getFinish());
			MinMaxDate date = new MinMaxDate(task);

			if (element.hasChild()) {
				for (ProducteTemplateElement e : element.getChilds()) {
					MinMaxDate childDate = fill(graphData, e);
					date.set(childDate);
				}
			}
			return date;
		}
	}

	private static class MinMaxDate {
		private Date minDate;
		private Date maxDate;

		private MinMaxDate() {

		}

		private MinMaxDate(ResourceTask task) {
			this.minDate = task.getStart();
			this.maxDate = task.getFinish();
		}

		private MinMaxDate(ProductElementTask task) {
			this.minDate = new Date(task.getStart().getTime());
			this.maxDate = new Date(task.getFinish().getTime());
		}

		private void set(MinMaxDate date) {
			if (date == null || date.maxDate == null || date.minDate == null)
				return;

			if (minDate == null || minDate.after(date.minDate))
				minDate = date.minDate;

			if (maxDate == null || maxDate.before(date.maxDate))
				maxDate = date.maxDate;
		}

		private int getDuration() {
			return DateUtils.diffInDays(minDate, maxDate);
		}
	}
}
