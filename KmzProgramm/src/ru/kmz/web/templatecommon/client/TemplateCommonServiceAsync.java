package ru.kmz.web.templatecommon.client;

import java.util.List;

import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateCommonServiceAsync {

	void getTemplateList(AsyncCallback<List<TemplateTreeDataProxy>> callback);

}
