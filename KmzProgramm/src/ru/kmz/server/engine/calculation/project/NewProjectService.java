package ru.kmz.server.engine.calculation.project;

import java.util.Date;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.engine.calculation.resources.ICalcucateExecutionServiceInterface;
import ru.kmz.server.engine.calculation.resources.ResourceTask;

public class NewProjectService {

	private ICalcucateExecutionServiceInterface service;
	private Order order;

	public NewProjectService() {
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setService(ICalcucateExecutionServiceInterface service) {
		this.service = service;
	}

	public void save(Template template, Date date) {
		service.calculate(template, date);

		ProducteTemplateElement root = template.getRootElement();

		fill(null, root);

	}

	private void fill(ProductElementTask parentElementTask, ProducteTemplateElement element) {
		ResourceTask resourceTask = service.getResult().get(element);
		ProductElementTask task;
		if (parentElementTask == null) {
			task = new ProductElementTask(element.getName(), element.getDuration(), element.getResourceType(),
					resourceTask, order);
		} else {
			task = new ProductElementTask(element.getName(), element.getDuration(), element.getResourceType(),
					resourceTask, parentElementTask);
		}
		task = OrderDataUtils.edit(task);

		if (element.hasChild()) {
			for (ProducteTemplateElement e : element.getChilds()) {
				fill(task, e);
			}
		}
	}
}
