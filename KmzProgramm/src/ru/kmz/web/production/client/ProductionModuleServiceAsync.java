package ru.kmz.web.production.client;

import java.util.List;

import ru.kmz.web.projectscommon.shared.ProductionProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductionModuleServiceAsync {

	void getActiveProductions(AsyncCallback<List<ProductionProxy>> callback);

	void compliteProduction(String id, AsyncCallback<Void> callback);

}
