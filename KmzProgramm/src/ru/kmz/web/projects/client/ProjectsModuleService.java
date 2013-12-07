package ru.kmz.web.projects.client;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;
import ru.kmz.web.projects.shared.UpdateProjectElementTaskParams;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsModule.rpc")
public interface ProjectsModuleService extends RemoteService {

	GanttData getCurrentTasks(GanttDataFilter filter);

	void save(CalculatorInputDataProxy input);

	void deleteProduct(long id);

	void updateDate(long id, UpdateProjectElementTaskParams params);

}
