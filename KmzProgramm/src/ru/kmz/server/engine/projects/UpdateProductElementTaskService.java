package ru.kmz.server.engine.projects;

import java.util.Date;

import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.utils.DateUtils;

public class UpdateProductElementTaskService {

	private String key;
	private Date date;

	public UpdateProductElementTaskService(String key, Date date) {
		this.key = key;
		this.date = date;
	}

	public void update() {
		ProductElementTask product = ProductElementTaskDataUtils.getTaskFull(key);
		int offset = DateUtils.diffInDays(product.getFinish(), date);
		recUpdate(product, offset);
		ProductElementTaskDataUtils.editFull(product);
	}

	private void recUpdate(ProductElementTask task, int offset) {
		task.addOffset(offset);
		if (task.hasChild()) {
			for (ProductElementTask e : task.getChilds()) {
				recUpdate(e, offset);
			}
		}
	}

}
