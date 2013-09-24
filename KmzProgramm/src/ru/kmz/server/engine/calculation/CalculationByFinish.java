package ru.kmz.server.engine.calculation;

import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultRowProxy;

public class CalculationByFinish {

	public static CalculatorResultDataProxy getCalculationByFinishDate(Template template, Date finishDate) {
		ProducteTemplate product = template.getProduct();
		CalculatorResultDataProxy data = new CalculatorResultDataProxy();
		addElementToResult(data, product.getChilds(), "", finishDate);
		return data;
	}

	private static void addElementToResult(CalculatorResultDataProxy data, List<ProducteTemplateElement> elements,
			String prefix, Date finish) {
		for (ProducteTemplateElement element : elements) {
			Date start = CalculationUtils.getOffsetDate(finish, -element.getDuration());
			data.add(new CalculatorResultRowProxy(1, prefix + element.getName(), element.getResourceType(), start,
					finish));
			if (element.hasChild()) {
				addElementToResult(data, element.getChilds(), prefix + "-", start);
			}
		}
	}

}
