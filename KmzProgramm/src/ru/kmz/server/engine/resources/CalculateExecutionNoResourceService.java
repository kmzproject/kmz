package ru.kmz.server.engine.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculator.CalendarOffsetService;
import ru.kmz.server.utils.DateUtils;

public class CalculateExecutionNoResourceService implements ICalcucateExecutionServiceInterface {

	private Map<ProductTemplateElement, ResourceTask> result;
	private boolean useWeekend;
	private CalendarOffsetService calendarService;

	public CalculateExecutionNoResourceService() {
		result = new HashMap<ProductTemplateElement, ResourceTask>();
	}

	public void setUseWeekend(boolean useWeekend) {
		this.useWeekend = useWeekend;
	}

	@Override
	public void calculate(Template template, Date date) {
		if (useWeekend) {
			calendarService = new CalendarOffsetService();
		}
		calculateElementStart(template.getRootElement(), date);
		printMap();
	}

	public Date calculateElementStart(ProductTemplateElement element, Date finish) {

		Date start = getStartOffset(finish, element);

		ResourceTask task = new ResourceTask(start, finish, element.getDuration());

		if (element.hasChild()) {
			for (ProductTemplateElement e : element.getChilds()) {
				calculateElementStart(e, start);
			}
		}

		result.put(element, task);
		return task.getStart();
	}

	private Date getStartOffset(Date finish, ProductTemplateElement element) {
		if (useWeekend) {
			return calendarService.getOffsetDate(finish, -element.getDuration());
		} else {
			return DateUtils.getOffsetDate(finish, -element.getDuration());
		}
	}

	@Override
	public Map<ProductTemplateElement, ResourceTask> getResult() {
		return result;
	}

	public void printMap() {
		for (ProductTemplateElement element : result.keySet()) {
			System.out.println(element + " -> " + result.get(element));
		}
	}

}
