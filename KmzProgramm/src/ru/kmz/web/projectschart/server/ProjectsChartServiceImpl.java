package ru.kmz.web.projectschart.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.projectschart.client.ProjectsChartService;
import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

public class ProjectsChartServiceImpl implements ProjectsChartService {

	@Override
	public List<FunctioningCapacityProxy> getFunctioningCapacity() {
		List<FunctioningCapacityProxy> list = new ArrayList<FunctioningCapacityProxy>();
		list.add(new FunctioningCapacityProxy("", 3));
		return list;
	}

}
