package ru.kmz.server.engine.calculation.gant;

import java.util.Date;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.server.engine.calculation.resources.ICalcucateExecutionServiceInterface;
import ru.kmz.server.engine.calculation.resources.ResourceTask;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.ganttcommon.shared.IGraphDataContainer;

public class GantCalculationService {

	private GanttData data;
	private ICalcucateExecutionServiceInterface service;

	public GantCalculationService() {
	}

	public GanttData getGantData() {
		return data;
	}

	public void setService(ICalcucateExecutionServiceInterface service) {
		this.service = service;
	}

	public GanttData calculate(Template template, Date date) {
		service.calculate(template, date);

		data = new GanttData("Расчет по шаблону " + template.getName());

		ProducteTemplateElement root = template.getRootElement();

		MinMaxDate minMaxDate = fill(data, root);

		data.setDateStart(minMaxDate.minDate);
		data.setDateFinish(minMaxDate.maxDate);

		return data;
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

		private void set(MinMaxDate date) {
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
