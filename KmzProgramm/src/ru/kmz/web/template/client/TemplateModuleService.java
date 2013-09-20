package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateModule")
public interface TemplateModuleService extends RemoteService {

	TemplateTreeDataProxy getData();
}
