package ru.kmz.web.projectschart.client;

import java.util.List;

import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectsChartServiceAsync {

	void getFunctioningCapacity(AsyncCallback<List<FunctioningCapacityProxy>> callback);

}
