package ru.kmz.web.template.server;

import ru.kmz.web.template.client.TemplateModuleService;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TemplateModuleServiceImpl extends RemoteServiceServlet implements TemplateModuleService {

	@Override
	public TemplateTreeDataProxy getData() {
		TemplateTreeDataProxy data = new TemplateTreeDataProxy(TemplateTreeDataExample.getRoot());
		return data;
	}

}
