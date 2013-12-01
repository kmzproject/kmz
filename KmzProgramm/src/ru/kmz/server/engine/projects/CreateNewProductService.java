package ru.kmz.server.engine.projects;

import java.util.Date;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.calculator.CalendarOffsetService;

public class CreateNewProductService {

	private Order order;
	private int count;
	private int taskNumber;
	private OffsetService offcetService;

	public CreateNewProductService() {
		taskNumber = 0;
		offcetService = new OffsetService();
	}

	public void setUseWeekend(boolean useWeekend) {
		if (useWeekend) {
			offcetService = new CalendarOffsetService();
		} else {
			offcetService = new OffsetService();
		}
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ProductElementTask save(Template template, Date finishDate) {
		ProductTemplateElement rootTemplate = template.getRootElement();
		ProductElementTask product = createProductElementTask(rootTemplate, null, finishDate);
		updateOrderStartFinish(product);
		return product;
	}

	private ProductElementTask createProductElementTask(ProductTemplateElement templateElement, ProductElementTask parentTask, Date finishDate) {
		ProductElementTask task;
		if (parentTask == null) {
			task = new ProductElementTask(templateElement, order.getId());
		} else {
			task = new ProductElementTask(templateElement, parentTask);
		}

		task.setCount(count);
		taskNumber++;
		task.setNumberInfo(taskNumber, order.getCode());

		task.setFinish(finishDate);
		Date startDate = offcetService.getOffsetDate(finishDate, -templateElement.getDuration());
		task.setStart(startDate);
		task = ProductElementTaskDataUtils.edit(task);

		if (templateElement.hasChild()) {
			for (ProductTemplateElement e : templateElement.getChilds()) {
				createProductElementTask(e, task, task.getStart());
			}
			if (templateElement.getDuration() == 0) {
				task.setStart(getChildsStart(task));
				task = ProductElementTaskDataUtils.edit(task);
			}
		}
		return task;
	}

	private Date getChildsStart(ProductElementTask task) {
		Date startDate = task.getStart();
		if (task.hasChild()) {
			for (ProductElementTask t : task.getChilds()) {
				Date childStartDate = getChildsStart(t);
				if (startDate.after(childStartDate)) {
					startDate = childStartDate;
				}
			}
		}
		return startDate;
	}

	private void updateOrderStartFinish(ProductElementTask product) {
		boolean isEdit = false;
		if (order.getFinish() == null || order.getFinish().before(product.getFinish())) {
			order.setFinish(product.getFinish());
			isEdit = true;
		}
		if (order.getStart() == null || order.getStart().after(product.getStart())) {
			order.setStart(product.getStart());
			isEdit = true;
		}
		if (isEdit) {
			order = OrderDataUtils.edit(order);
			// TODO: запись в историю - изменилась дата заказа
		}
	}

}
