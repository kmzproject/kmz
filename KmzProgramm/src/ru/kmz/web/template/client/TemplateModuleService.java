package ru.kmz.web.template.client;

import ru.kmz.web.templatecommon.client.TemplateCommonService;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateCommon.rpc")
public interface TemplateModuleService extends TemplateCommonService {

	TemplateTreeDataProxy getData(long key);

	TemplateTreeNodeBaseProxy createNewTemplateTreeNode(long parentKey);

	void deleteTemplateTreeNode(long key);

	TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy);

	void editTemplate(TemplateTreeDataProxy proxy);
}
