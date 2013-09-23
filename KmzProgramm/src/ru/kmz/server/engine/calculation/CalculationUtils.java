package ru.kmz.server.engine.calculation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultRowProxy;

public class CalculationUtils {

	public static CalculatorResultDataProxy getCalculation(CalculatorInputDataProxy input) {
		List<Template> list = TemplateDataUtils.getAllTemplates();
		Template temlpate = list.get(0);
		ProducteTemplate product = temlpate.getProduct();
		CalculatorResultDataProxy data = new CalculatorResultDataProxy();
		addElementToResult(data, product.getChilds(), "", input.getFinishDate());
		return data;
	}

	private static void addElementToResult(CalculatorResultDataProxy data, List<ProducteTemplateElement> elements,
			String prefix, Date finish) {
		for (ProducteTemplateElement element : elements) {
			Date start = getOffsetDate(finish, element.getDuration());
			data.add(new CalculatorResultRowProxy(1, prefix + element.getName(), element.getResourceType(), start,
					finish));
			if (element.hasChild()) {
				addElementToResult(data, element.getChilds(), prefix + "-", start);
			}
		}
	}

	private static final Calendar c = Calendar.getInstance();

	private static Date getOffsetDate(Date finish, int days) {
		c.setTime(finish);
		c.add(Calendar.DATE, -days);
		return c.getTime();
	}
}
