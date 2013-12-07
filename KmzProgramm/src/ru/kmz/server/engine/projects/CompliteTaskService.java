package ru.kmz.server.engine.projects;

import ru.kmz.server.data.model.ProductElementTask;
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

		UpdateDoneValueService updateDoneService = new UpdateDoneValueService();
		updateDoneService.update(task);

		HistoryUtils.setFact(task, oldFact);
	}
}
