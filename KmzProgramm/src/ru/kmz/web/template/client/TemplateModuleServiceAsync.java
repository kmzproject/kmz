package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateModuleServiceAsync {

	void getData(AsyncCallback<TemplateTreeDataProxy> callback);

}
