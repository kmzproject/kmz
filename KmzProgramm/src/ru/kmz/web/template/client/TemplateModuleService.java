package ru.kmz.web.template.client;

import java.util.List;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateModule")
public interface TemplateModuleService extends RemoteService {

	List<TemplateTreeDataProxy> getTemplateList();
	
	TemplateTreeDataProxy getData(String keyId);
}
