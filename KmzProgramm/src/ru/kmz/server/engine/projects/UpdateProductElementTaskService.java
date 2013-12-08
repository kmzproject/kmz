package ru.kmz.server.engine.projects;

import java.util.Date;

import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.calculator.CalendarOffsetService;
import ru.kmz.server.engine.projects.update.UpdateOrderStartFinishService;
import ru.kmz.server.utils.DateUtils;

public class UpdateProductElementTaskService {

	private long key;
	private Date date;
	private OffsetService offcetService;

	public UpdateProductElementTaskService(long key, Date date) {
		this.key = key;
		this.date = DateUtils.getDateNoTime(date);
		this.offcetService = new CalendarOffsetService();
	}

	public void update() {
		ProductElementTask product = ProductElementTaskDataUtils.getTaskFull(key);
		recSetNewFinishDate(product, date);
		ProductElementTaskDataUtils.editFull(product);

		UpdateOrderStartFinishService updateOrderStartFinishService = new UpdateOrderStartFinishService();
		updateOrderStartFinishService.updateByOrderId(product.getOrderId());
	}

	private void recSetNewFinishDate(ProductElementTask task, Date finishDate) {
		int workDiration = task.getDuration();
		Date startDate = offcetService.getOffsetDate(finishDate, -workDiration);
		task.setFinish(finishDate);
		task.setStart(startDate);
		if (task.hasChild()) {
			for (ProductElementTask t : task.getChilds()) {
				recSetNewFinishDate(t, startDate);
			}
			if (workDiration == 0) {
				startDate = CreateNewProductService.getChildsStart(task);
				task.setStart(startDate);
			}
		}
	}

}
