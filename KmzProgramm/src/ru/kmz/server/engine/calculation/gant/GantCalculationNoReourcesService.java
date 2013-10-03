package ru.kmz.server.engine.calculation.gant;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class GantCalculationNoReourcesService {

	private Date minDate;
	private GanttData data;

	public GantCalculationNoReourcesService() {
	}

	public GanttData getGantData() {
		return data;
	}

	public GanttData calculateStart(Template template, Date startDate) {
		ProducteTemplate product = template.getProduct();
		int duration = getDuration(product.getChilds());
		Date finishDate = CalculationUtils.getOffsetDate(startDate, duration);
		return calculateFinish(template, finishDate);
	}

	public GanttData calculateFinish(Template template, Date finishDate) {
		data = new GanttData("Расчет по шаблону " + template.getName());
		minDate = finishDate;

		ProducteTemplate product = template.getProduct();
		GraphData wbsProducte = product.asGraphDataProxy();

		for (ProducteTemplateElement element : product.getChilds()) {
			wbsProducte.addChild(getWbs(element, finishDate));
		}

		int duration = getDuration(product.getChilds());
		wbsProducte.setDuration(duration);
		wbsProducte.setPlanStart(CalculationUtils.getOffsetDate(finishDate, -duration));
		wbsProducte.setPlanFinish(finishDate);

		data.add(wbsProducte);

		data.setDateStart(minDate);
		data.setDateFinish(finishDate);

		return data;
	}

	private GraphData getWbs(ProducteTemplateElement element, Date finish) {
		GraphData wbs = element.asGraphDataProxy();
		Date start = CalculationUtils.getOffsetDate(finish, -element.getDuration());
		for (ProducteTemplateElement e : element.getChilds()) {
			if (e.hasChild()) {
				GraphData cWbs = getWbs(e, start);
				wbs.addChild(cWbs);
			} else {
				GraphData cActivity = getActivity(e, start);
				wbs.addChild(cActivity);
			}
		}
		wbs.setPlanFinish(finish);
		wbs.setPlanStart(start);
		return wbs;
	}

	private GraphData getActivity(ProducteTemplateElement element, Date finish) {
		GraphData activity = element.asGraphDataProxy();
		Date start = CalculationUtils.getOffsetDate(finish, -element.getDuration());
		if (start.before(minDate))
			minDate = start;
		activity.setPlanStart(start);
		activity.setPlanFinish(finish);
		return activity;
	}

	private static int getDuration(List<ProducteTemplateElement> elements) {
		int durationMax = 0;
		for (ProducteTemplateElement element : elements) {
			int duration = 0;
			if (element.hasChild()) {
				duration = getDuration(element.getChilds());
			}
			duration += element.getDuration();
			if (duration > durationMax)
				durationMax = duration;
		}
		return durationMax;
	}
}
