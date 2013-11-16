package ru.kmz.web.projectschart.client;

import java.util.List;

import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsChart")
public interface ProjectsChartService extends RemoteService {

	List<FunctioningCapacityProxy> getFunctioningCapacity();

}
