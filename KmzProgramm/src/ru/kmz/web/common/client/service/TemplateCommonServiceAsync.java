package ru.kmz.web.common.client.service;

import java.util.List;

import ru.kmz.web.common.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateCommonServiceAsync {

	void getTemplateList(AsyncCallback<List<TemplateTreeDataProxy>> callback);

}
