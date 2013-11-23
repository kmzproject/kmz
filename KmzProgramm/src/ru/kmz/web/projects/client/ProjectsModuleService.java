package ru.kmz.web.projects.client;

import java.util.Date;

import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsModule.rpc")
public interface ProjectsModuleService extends RemoteService {

	GanttData getCurrentTasks();

	GanttData getGantResultData(CalculatorInputDataProxy input);

	void save(CalculatorInputDataProxy input);

	void deleteProduct(String id);

	void updateDate(String id, Date date);

}
