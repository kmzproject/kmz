package ru.kmz.web.template.client;

import ru.kmz.web.templatecommon.client.TemplateCommonServiceAsync;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateModuleServiceAsync extends TemplateCommonServiceAsync {

	void getData(String key, AsyncCallback<TemplateTreeDataProxy> callback);

	void createNewTemplateTreeNode(String parentKey, AsyncCallback<TemplateTreeNodeBaseProxy> callback);

	void deleteTemplateTreeNode(String key, AsyncCallback<Void> callback);

	void save(TemplateTreeNodeBaseProxy proxy, AsyncCallback<TemplateTreeNodeBaseProxy> callback);

}
