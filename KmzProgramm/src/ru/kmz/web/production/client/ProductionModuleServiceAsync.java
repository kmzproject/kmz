package ru.kmz.web.production.client;

import java.util.List;

import ru.kmz.web.projectscommon.client.ProjectsCommonServiceAsync;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.ProductionProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductionModuleServiceAsync extends ProjectsCommonServiceAsync {

	void getActiveProductions(ProductElementTaskGridFilter filter, AsyncCallback<List<ProductionProxy>> callback);

	void compliteProduction(long id, AsyncCallback<Void> callback);
}
