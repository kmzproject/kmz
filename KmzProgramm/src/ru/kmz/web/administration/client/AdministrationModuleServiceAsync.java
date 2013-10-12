package ru.kmz.web.administration.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdministrationModuleServiceAsync {

	void recreateData(AsyncCallback<Void> callback);

}
