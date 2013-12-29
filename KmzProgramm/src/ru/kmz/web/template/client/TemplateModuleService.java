package ru.kmz.web.template.client;

import ru.kmz.web.common.client.service.TemplateCommonService;
import ru.kmz.web.common.shared.TemplateTreeDataProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateCommon.rpc")
public interface TemplateModuleService extends TemplateCommonService {

	TemplateTreeDataProxy getData(long key);

	TemplateTreeNodeBaseProxy createNewTemplateTreeNode(long parentKey);

	void deleteTemplateTreeNode(long key);

	TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy);

	void editTemplate(TemplateTreeDataProxy proxy);

	void deleteTemplate(long key);

	TemplateTreeDataProxy copyTemplate(long key);
}
