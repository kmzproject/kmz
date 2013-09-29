package ru.kmz.web.resources.client;

import java.util.List;

import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResourcesModuleServiceAsync {

	void getAllResources(AsyncCallback<List<ResourceProxy>> callback);

	void editResource(ResourceProxy resource, AsyncCallback<ResourceProxy> callback);

}
