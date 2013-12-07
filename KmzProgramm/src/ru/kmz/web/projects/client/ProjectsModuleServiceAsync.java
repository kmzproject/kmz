package ru.kmz.web.projects.client;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;
import ru.kmz.web.projects.shared.UpdateProjectElementTaskParams;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectsModuleServiceAsync {

	void getCurrentTasks(GanttDataFilter filter, AsyncCallback<GanttData> callback);

	void save(CalculatorInputDataProxy input, AsyncCallback<Void> callback);

	void deleteProduct(long id, AsyncCallback<Void> callback);

	void updateDate(long id, UpdateProjectElementTaskParams params, AsyncCallback<Void> callback);
}
