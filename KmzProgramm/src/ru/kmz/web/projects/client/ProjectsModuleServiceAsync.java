package ru.kmz.web.projects.client;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectsModuleServiceAsync {

	void getCurrentTasks(AsyncCallback<GanttData> callback);

	void getGantResultData(CalculatorInputDataProxy input, AsyncCallback<GanttData> callback);

	void save(CalculatorInputDataProxy input, AsyncCallback<Void> callback);

	void setCompliteTaskPersents(String id, int persents, AsyncCallback<Void> callback);

	void deleteProduct(String id, AsyncCallback<Void> callback);
}
