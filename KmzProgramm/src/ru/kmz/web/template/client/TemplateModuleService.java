package ru.kmz.web.template.client;

import ru.kmz.web.templatecommon.client.TemplateCommonService;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateCommon.rpc")
public interface TemplateModuleService extends TemplateCommonService {

	TemplateTreeDataProxy getData(String key);

	TemplateTreeNodeBaseProxy createNewTemplateTreeNode(String parentKey);

	void deleteTemplateTreeNode(String key);

	TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy);

	void editTemplate(TemplateTreeDataProxy proxy);
}
