package ru.kmz.server.engine.gant;

import java.util.Date;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.resources.ICalcucateExecutionServiceInterface;
import ru.kmz.server.engine.resources.ResourceTask;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.ganttcommon.shared.IGraphDataContainer;

public class GanttTemplateCalculationService extends GanttProjectsService {

	private ICalcucateExecutionServiceInterface service;
	private Template template;
	private Date date;

	public void setService(ICalcucateExecutionServiceInterface service) {
		this.service = service;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public MinMaxDate process() {
		service.calculate(template, date);
		ProductTemplateElement root = template.getRootElement();

		MinMaxDate minMaxTemplateDate = fill(data, root);

		MinMaxDate minMaxDate = super.process();
		minMaxDate.set(minMaxTemplateDate);

		return minMaxDate;
	}

	@Override
	protected void createGanttData() {
		data = new GanttData("Расчет по шаблону " + template.getName());
	}

	private MinMaxDate fill(IGraphDataContainer rootGraphData, ProductTemplateElement element) {
		GraphData graphData = element.asGraphDataProxy();
		rootGraphData.addChild(graphData);

		if (graphData.isFolder()) {
			MinMaxDate date = new MinMaxDate();
			if (element.hasChild()) {
				for (ProductTemplateElement e : element.getChilds()) {
					MinMaxDate childDate = fill(graphData, e);
					date.set(childDate);
				}
			}
			graphData.setPlanStart(date.getMinDate());
			graphData.setPlanFinish(date.getMaxDate());
			graphData.setDuration(date.getDuration());
			return date;

		} else {
			ResourceTask task = service.getResult().get(element);

			graphData.setPlanStart(task.getStart());
			graphData.setPlanFinish(task.getFinish());
			MinMaxDate date = new MinMaxDate(task);

			if (element.hasChild()) {
				for (ProductTemplateElement e : element.getChilds()) {
					MinMaxDate childDate = fill(graphData, e);
					date.set(childDate);
				}
			}
			return date;
		}
	}
}
