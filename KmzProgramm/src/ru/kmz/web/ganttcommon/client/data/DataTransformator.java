package ru.kmz.web.ganttcommon.client.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

import com.sencha.gxt.core.client.util.DateWrapper;

public class DataTransformator implements IDemoData {

	private GanttData data;

	public DataTransformator(GanttData data) {
		this.data = data;
	}

	@Override
	public Task getTasks() {
		DateWrapper dw = new DateWrapper(new Date()).clearTime().addDays(-7);

		ArrayList<Task> list = new ArrayList<Task>();
		for (GraphData child : data.getChilds()) {
			Task task = getTask(child, dw);
			list.add(task);
		}
		Task root = new Task(list);
		return root;
	}

	private Task getTask(GraphData data, DateWrapper dw) {
		Task task = new Task(data.getId(), data.getName(), data.getPlanStart(), data.getPlanFinish(), data.getDuration(), data.getComplite(),
				data.getTaskType(), data.getResourceType());
		for (GraphData child : data.getChilds()) {
			Task childTask = getTask(child, dw);
			task.addChild(childTask);
		}
		return task;
	}

	@Override
	public List<Dependency> getDependencies() {
		List<Dependency> list = new ArrayList<Dependency>();
		return list;
	}

	@Override
	public Date getDateStart() {
		return data.getDateStart();
	}

	@Override
	public Date getDateFinish() {
		return data.getDateFinish();
	}

}
