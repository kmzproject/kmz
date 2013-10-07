package ru.kmz.server.engine.calculation.gant;

import java.util.Date;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.server.engine.calculation.resources.ICalcucateExecutionServiceInterface;
import ru.kmz.server.engine.calculation.resources.ResourceTask;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class GantCalculationService {

	private GanttData data;
	private ICalcucateExecutionServiceInterface service;
	private Date minData;
	private Date maxData;

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

		minData = date;
		maxData = date;
		data = new GanttData("Расчет по шаблону " + template.getName());

		ProducteTemplateElement root = template.getRootElement();
		GraphData wbsRoot = root.asGraphDataProxy();

		for (ProducteTemplateElement element : root.getChilds()) {
			fill(wbsRoot, element);
		}

		wbsRoot.setPlanStart(minData);
		wbsRoot.setPlanFinish(maxData);
		wbsRoot.setDuration(DateUtils.diffInDays(minData, maxData));

		data.add(wbsRoot);

		data.setDateStart(minData);
		data.setDateFinish(maxData);

		return data;
	}

	private void fill(GraphData rootGraphData, ProducteTemplateElement element) {
		GraphData graphData = element.asGraphDataProxy();
		ResourceTask task = service.getResult().get(element);
		graphData.setPlanStart(task.getStart());
		graphData.setPlanFinish(task.getFinish());

		rootGraphData.addChild(graphData);

		if (task.getFinish().after(maxData))
			maxData = task.getFinish();
		if (task.getStart().before(minData))
			minData = task.getStart();

		if (element.hasChild()) {
			for (ProducteTemplateElement e : element.getChilds()) {
				fill(graphData, e);
			}
		}
	}
}
