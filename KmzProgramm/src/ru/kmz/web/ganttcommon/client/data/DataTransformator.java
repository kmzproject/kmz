package ru.kmz.web.ganttcommon.client.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.ganttcommon.shared.ActivityData;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.WbsData;

import com.gantt.client.config.GanttConfig.TaskType;
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
		for (WbsData wbs : data.getWbss()) {
			Task task = getTaskByWbs(wbs, dw);
			list.add(task);
		}
		Task root = new Task(list);
		return root;
	}

	private Task getTaskByWbs(WbsData wbs, DateWrapper dw) {
		Task task = new Task(wbs.getId(), wbs.getName(), wbs.getPlanStart(), wbs.getPlanFinish(), wbs.getDuration(),
				wbs.getComplite(), wbs.isMilestone() ? TaskType.MILESTONE : wbs.isFolder() ? TaskType.PARENT
						: TaskType.LEAF, wbs.getResourceType());
		for (ActivityData activity : wbs.getActivities()) {
			Task childTask = getTaskByActivity(activity, dw);
			task.addChild(childTask);
		}
		for (WbsData child : wbs.getChilds()) {
			Task childTask = getTaskByWbs(child, dw);
			task.addChild(childTask);
		}
		return task;
	}

	private Task getTaskByActivity(ActivityData activity, DateWrapper dw) {
		Task task = new Task(activity.getId(), activity.getName(), activity.getPlanStart(), activity.getPlanFinish(),
				activity.getDuration(), activity.getComplite(), activity.isMilestone() ? TaskType.MILESTONE
						: TaskType.LEAF, activity.getResourceType());
		return task;
	}

	@Override
	public List<Dependency> getDependencies() {
		List<Dependency> list = new ArrayList<Dependency>();
		return list;
	}

}
