package ru.kmz.server.engine.projects.update;

import ru.kmz.server.data.model.IProjectTask;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.projects.DurationComplite;

public class UpdateDoneValueService {

	public void update(ProductElementTask task) {
		updateByOrderId(task.getOrderId());
	}

	public void update(Order order) {
		OrderDataUtils.loadOrder(order);
		calculatePersentsDone(order);
	}

	public void updateByOrderId(long orderId) {
		Order order = OrderDataUtils.getOrder(orderId);
		calculatePersentsDone(order);
	}

	private static DurationComplite calculatePersentsDone(IProjectTask task) {
		DurationComplite dc = new DurationComplite();
		if (task.hasChild()) {
			for (IProjectTask child : task.getChilds()) {
				dc.add(calculatePersentsDone(child));
			}
		}
		if (!task.isFolder()) {
			dc.add(new DurationComplite(task.getDuration(), task.getDone()));
		} else {
			task.setDone(dc.getPersent());
			if (task instanceof ProductElementTask) {
				ProductElementTaskDataUtils.edit((ProductElementTask) task);
			} else {
				OrderDataUtils.edit((Order) task);
			}
		}
		return dc;
	}

}
