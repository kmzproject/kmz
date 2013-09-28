package ru.kmz.web.ganttcommon.client.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.shared.HasResourceType;

import com.gantt.client.config.GanttConfig.TaskType;

public class Task implements HasResourceType {
	String id;
	String name;
	Date startDateTime;
	Date endDateTime;
	int duration;
	int percentDone;
	TaskType taskType;
	String resourceType;

	private List<Task> children = new ArrayList<Task>();

	public Task(List<Task> children) {
		setChildren(children);
	}

	public Task(String id, String name, Date start, Date endDateTime, int duration, int percentDone, TaskType taskType,
			String resourceType) {
		this.id = id;
		this.name = name;
		this.startDateTime = start;
		this.endDateTime = endDateTime;
		this.duration = duration;
		this.percentDone = percentDone;
		this.taskType = taskType;
		this.resourceType = resourceType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPercentDone() {
		return percentDone;
	}

	public void setPercentDone(int percentDone) {
		this.percentDone = percentDone;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public List<Task> getChildren() {
		return children;
	}

	public void setChildren(List<Task> children) {
		this.children = children;
	}

	public void addChild(Task child) {
		getChildren().add(child);
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public String getResourceType() {
		return resourceType;
	}
}
