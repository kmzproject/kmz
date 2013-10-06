package ru.kmz.server.engine.calculation.gant;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.server.engine.calculation.resources.CalculateExecutaionFirstFreeService;
import ru.kmz.server.engine.calculation.resources.ResourceTask;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class GantCalculationResourcesService {

	private GanttData data;
	private CalculateExecutaionFirstFreeService service;
	private Date minData;
	private Date maxData;

	public GantCalculationResourcesService(List<Resource> list) {
		service = new CalculateExecutaionFirstFreeService(list);
	}

	public GanttData getGantData() {
		return data;
	}

	public GanttData calculateStart(Template template, Date startDate) {
		// тут допущение что у нас всегда начинается проект с заданой даты это
		// возможно если все листы не привязаны к ремурсам а потка что у нас
		// так/
		service.calculateElementFinish(template.getRootElement(), startDate);

		minData = startDate;
		maxData = startDate;
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

		if (element.hasChild()) {
			for (ProducteTemplateElement e : element.getChilds()) {
				fill(graphData, e);
			}
		}
	}
}
