package ru.kmz.web.ganttcommon.client.data;

import com.gantt.client.model.TaskProperties;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;

public interface TaskProps extends TaskProperties<Task> {

	public static final TaskProps props = GWT.create(TaskProps.class);

	ValueProvider<Task, String> durationCol();
}
