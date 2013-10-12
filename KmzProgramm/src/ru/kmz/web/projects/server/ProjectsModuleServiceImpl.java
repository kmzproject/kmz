package ru.kmz.web.projects.server;

import ru.kmz.server.engine.gant.GanttProjectsService;
import ru.kmz.server.engine.projects.GetOrdersService;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.client.ProjectsModuleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ProjectsModuleServiceImpl extends RemoteServiceServlet implements ProjectsModuleService {

	@Override
	public GanttData getCurrentTasks() {
		GanttProjectsService service = new GanttProjectsService();

		service.setGetOrdersService(new GetOrdersService());
		service.calculate();
		GanttData data = service.getGantData();
		return data;
	}

}
