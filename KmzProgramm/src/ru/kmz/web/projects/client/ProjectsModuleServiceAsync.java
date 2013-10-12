package ru.kmz.web.projects.client;

import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectsModuleServiceAsync {

	void getCurrentTasks(AsyncCallback<GanttData> callback);

}
