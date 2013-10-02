package ru.kmz.web.template.client;

import java.util.List;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateModuleServiceAsync {

	void getData(int keyId, AsyncCallback<TemplateTreeDataProxy> callback);

	void getTemplateList(AsyncCallback<List<TemplateTreeDataProxy>> callback);

}
