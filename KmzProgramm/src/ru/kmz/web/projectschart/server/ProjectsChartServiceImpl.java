package ru.kmz.web.projectschart.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.kmz.web.projectschart.client.ProjectsChartService;
import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

@SuppressWarnings("serial")
public class ProjectsChartServiceImpl extends RemoteServiceServlet implements ProjectsChartService {

	@Override
	public List<FunctioningCapacityProxy> getFunctioningCapacity() {
		List<FunctioningCapacityProxy> list = new ArrayList<FunctioningCapacityProxy>();
		list.add(new FunctioningCapacityProxy("Дата 1", 3));
		list.add(new FunctioningCapacityProxy("Дата 2", 4));
		list.add(new FunctioningCapacityProxy("Дата 3", 6));
		list.add(new FunctioningCapacityProxy("Дата 4", 2));
		return list;
	}

}
