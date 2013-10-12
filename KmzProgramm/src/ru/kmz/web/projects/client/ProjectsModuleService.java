package ru.kmz.web.projects.client;

import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsModule")
public interface ProjectsModuleService extends RemoteService {

	GanttData getCurrentTasks();
}
