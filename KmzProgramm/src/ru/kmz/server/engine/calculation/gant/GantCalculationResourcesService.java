package ru.kmz.server.engine.calculation.gant;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.resources.ResourceService;
import ru.kmz.server.engine.calculation.resources.ResourceTask;
import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.shared.ActivityData;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.WbsData;

public class GantCalculationResourcesService {

	private GanttData data;
	private ResourceService resourceService;
	private Date minData;
	private Date maxData;

	public GantCalculationResourcesService() {
		resourceService = new ResourceService();
	}

	public void setResourcesList(List<Resource> list) {
		resourceService.init(list);
	}

	public GanttData getGantData() {
		return data;
	}

	public GanttData calculateStart(Template template, Date startDate) {
		// тут допущение что у нас всегда начинается проект с заданой даты это
		// возможно если все листы не привязаны к ремурсам а потка что у нас
		// так/
		minData = startDate;
		maxData = startDate;
		data = new GanttData("Расчет по шаблону " + template.getName());

		ProducteTemplate product = template.getProduct();
		WbsData wbsProducte = product.asWbsDataProxy();

		for (ProducteTemplateElement element : product.getChilds()) {
			fill(wbsProducte, element, startDate);
		}

		wbsProducte.setPlanStart(minData);
		wbsProducte.setPlanFinish(maxData);
		wbsProducte.setDuration(10);

		data.add(wbsProducte);

		data.setDateStart(minData);
		data.setDateFinish(maxData);

		return data;
	}

	private Date fill(WbsData rootwbs, ProducteTemplateElement element, Date start) {
		if (element.hasChild()) {
			Date maxChildData = start;
			WbsData wbs = element.asWbsDataProxy();
			for (ProducteTemplateElement e : element.getChilds()) {
				Date finish = fill(wbs, e, start);
				if (finish.after(maxChildData)) {
					maxChildData = finish;
				}
			}
			Date currentWbsStart = maxChildData;
			if (ResourceTypesConsts.needResource(element.getResourceType()) && element.getDuration() != 0) {
				ResourceTask task = resourceService.getResentTask(element.getResourceType(), currentWbsStart,
						element.getDuration());
				currentWbsStart = task.getStart();
			}
			Date finish = CalculationUtils.getOffsetDate(currentWbsStart, element.getDuration());
			if (finish.after(maxData)) {
				maxData = finish;
			}
			wbs.setPlanFinish(finish);
			wbs.setPlanStart(currentWbsStart);

			rootwbs.addChild(wbs);
			return wbs.getPlanFinish();
		} else {
			Date currentActivityStart = start;
			if (ResourceTypesConsts.needResource(element.getResourceType()) && element.getDuration() != 0) {
				ResourceTask task = resourceService.getResentTask(element.getResourceType(), currentActivityStart,
						element.getDuration());
				start = task.getStart();
			}
			Date finish = CalculationUtils.getOffsetDate(start, element.getDuration());
			if (finish.after(maxData)) {
				maxData = finish;
			}

			ActivityData activity = element.asActivityDataProxy();
			activity.setPlanStart(start);
			activity.setPlanFinish(finish);
			rootwbs.addActivity(activity);
			return activity.getPlanFinish();
		}
	}
}
