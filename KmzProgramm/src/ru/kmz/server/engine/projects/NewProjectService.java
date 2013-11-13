package ru.kmz.server.engine.projects;

import java.util.Date;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.resources.ICalcucateExecutionServiceInterface;
import ru.kmz.server.engine.resources.ResourceTask;

public class NewProjectService {

	private ICalcucateExecutionServiceInterface service;
	private Order order;
	private int count;
	private int taskNumber;

	public NewProjectService() {
		taskNumber = 0;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setService(ICalcucateExecutionServiceInterface service) {
		this.service = service;
	}

	public ProductElementTask save(Template template, Date date) {
		service.calculate(template, date);

		ProductTemplateElement root = template.getRootElement();

		ProductElementTask task = fill(null, root);
		return task;

	}

	private ProductElementTask fill(ProductElementTask parentElementTask, ProductTemplateElement element) {
		ResourceTask resourceTask = service.getResult().get(element);
		ProductElementTask task;
		if (parentElementTask == null) {
			task = new ProductElementTask(element.getName(), element.getDuration(), element.getResourceType(), resourceTask, order);
		} else {
			task = new ProductElementTask(element.getName(), element.getDuration(), element.getResourceType(), resourceTask, parentElementTask);
		}
		task.setCount(count);
		taskNumber++;
		task.setNumberInfo(taskNumber, order.getCode());
		task = ProductElementTaskDataUtils.edit(task);

		if (element.hasChild()) {
			for (ProductTemplateElement e : element.getChilds()) {
				fill(task, e);
			}
		}
		return task;
	}
}
