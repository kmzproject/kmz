package ru.kmz.server.engine.calculation;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

public class CalculationByStart {

	public static CalculatorResultDataProxy getCalculationByFinishDate(Template template, Date startDate) {
		ProducteTemplate product = template.getProduct();
		int duration = getDuration(product.getChilds());
		Date finishDate = CalculationUtils.getOffsetDate(startDate, duration);
		return CalculationByFinish.getCalculationByFinishDate(template, finishDate);
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
