package ru.kmz.web.template.client;

import java.util.List;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateModule")
public interface TemplateModuleService extends RemoteService {

	List<TemplateTreeDataProxy> getTemplateList();

	TemplateTreeDataProxy getData(String key);

	TemplateTreeNodeBaseProxy createNewTemplateTreeNode(String parentKey);

	void deleteTemplateTreeNode(String key);

	TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy);
}
