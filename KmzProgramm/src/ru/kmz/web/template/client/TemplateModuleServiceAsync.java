package ru.kmz.web.template.client;

import ru.kmz.web.common.client.service.TemplateCommonServiceAsync;
import ru.kmz.web.common.shared.TemplateTreeDataProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TemplateModuleServiceAsync extends TemplateCommonServiceAsync {

	void getData(long key, AsyncCallback<TemplateTreeDataProxy> callback);

	void createNewTemplateTreeNode(long parentKey, AsyncCallback<TemplateTreeNodeBaseProxy> callback);

	void deleteTemplateTreeNode(long key, AsyncCallback<Void> callback);

	void save(TemplateTreeNodeBaseProxy proxy, AsyncCallback<TemplateTreeNodeBaseProxy> callback);

	void editTemplate(TemplateTreeDataProxy proxy, AsyncCallback<Void> callback);

	void deleteTemplate(long key, AsyncCallback<Void> callback);

	void copyTemplate(long key, AsyncCallback<TemplateTreeDataProxy> callback);
}
