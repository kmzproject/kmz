package ru.kmz.server.engine.gant;

import java.util.Date;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.web.gant.shared.gant.ActivityData;
import ru.kmz.web.gant.shared.gant.GanttData;
import ru.kmz.web.gant.shared.gant.WbsData;

public class GantCalculationService {

	private Date minDate;
	private GanttData data;

	public GantCalculationService() {
		data = new GanttData("Test2");
	}

	public GanttData getGantData() {
		return data;
	}

	public GanttData calculate(Template template, Date finishDate) {
		minDate = finishDate;

		ProducteTemplate producte = template.getProduct();
		for (ProducteTemplateElement element : producte.getChilds()) {
			data.add(getWbs(element, finishDate));
		}

		data.setDateStart(minDate);
		data.setDateFinish(finishDate);

		return data;
	}

	private WbsData getWbs(ProducteTemplateElement element, Date finish) {
		WbsData wbs = element.asWbsDataProxy();
		Date start = CalculationUtils.getOffsetDate(finish, -element.getDuration());
		for (ProducteTemplateElement e : element.getChilds()) {
			if (e.hasChild()) {
				WbsData cWbs = getWbs(e, start);
				wbs.addChild(cWbs);
			} else {
				ActivityData cActivity = getActivity(e, start);
				wbs.addActivity(cActivity);
			}
		}
		wbs.setPlanFinish(finish);
		wbs.setPlanStart(start);
		return wbs;
	}

	private ActivityData getActivity(ProducteTemplateElement element, Date finish) {
		ActivityData activity = element.asActivityDataProxy();
		Date start = CalculationUtils.getOffsetDate(finish, -element.getDuration());
		if (start.before(minDate))
			minDate = start;
		activity.setPlanStart(start);
		activity.setPlanFinish(finish);
		return activity;
	}
}
