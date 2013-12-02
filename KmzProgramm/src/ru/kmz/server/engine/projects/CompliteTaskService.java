package ru.kmz.server.engine.projects;

import ru.kmz.server.data.model.IProjectTask;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.utils.HistoryUtils;

public class CompliteTaskService {

	private long id;

	public CompliteTaskService(long id) {
		this.id = id;
	}

	public void complite(int value) {
		ProductElementTask task = ProductElementTaskDataUtils.getTask(id);
		int oldFact = task.getDone();
		task.setDone(value);
		ProductElementTaskDataUtils.edit(task);

		Order order = OrderDataUtils.getOrder(task.getOrderId());
		calculatePersentsDone(order);

		HistoryUtils.setFact(task, oldFact);
	}

	public static DurationComplite calculatePersentsDone(IProjectTask task) {
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
