package ru.kmz.web.projects.server;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.client.ProjectsModuleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class ProjectsModuleServiceImpl extends RemoteServiceServlet implements  ProjectsModuleService {

	@Override
	public GanttData getCurrentTasks() {
		return null;
	}


}
