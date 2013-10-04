package ru.kmz.web.template.client;

import java.util.List;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateModuleServiceAsync {

	void getData(String key, AsyncCallback<TemplateTreeDataProxy> callback);

	void getTemplateList(AsyncCallback<List<TemplateTreeDataProxy>> callback);

	void createNewTemplateTreeNode(String parentKey, AsyncCallback<TemplateTreeNodeBaseProxy> callback);

	void deleteTemplateTreeNode(String key, AsyncCallback<Void> callback);

	void save(TemplateTreeNodeBaseProxy proxy, AsyncCallback<TemplateTreeNodeBaseProxy> callback);

}
