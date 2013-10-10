package ru.kmz.server.engine.calculation.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;

public class CalculateExecutionNoResourceService implements ICalcucateExecutionServiceInterface {

	private Map<ProductTemplateElement, ResourceTask> result;

	public CalculateExecutionNoResourceService() {
		result = new HashMap<ProductTemplateElement, ResourceTask>();
	}

	@Override
	public void calculate(Template template, Date date) {
		calculateElementStart(template.getRootElement(), date);
	}

	public Date calculateElementStart(ProductTemplateElement element, Date finish) {

		Date start = CalculationUtils.getOffsetDate(finish, -element.getDuration());

		ResourceTask task = new ResourceTask(start, finish, element.getDuration());

		if (element.hasChild()) {
			for (ProductTemplateElement e : element.getChilds()) {
				calculateElementStart(e, start);
			}
		}

		result.put(element, task);
		return task.getStart();
	}

	@Override
	public Map<ProductTemplateElement, ResourceTask> getResult() {
		return result;
	}

}
