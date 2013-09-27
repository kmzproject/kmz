package ru.kmz.web.template.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.generator.TemplateGenerator;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.web.template.client.TemplateModuleService;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TemplateModuleServiceImpl extends RemoteServiceServlet implements TemplateModuleService {

	@Override
	public List<TemplateTreeDataProxy> getTemplateList() {
		List<Template> templates = TemplateDataUtils.getAllTemplates();
		List<TemplateTreeDataProxy> list = new ArrayList<TemplateTreeDataProxy>();
		for (Template template : templates) {
			list.add(template.asProxy());
		}
		return list;
	}

	@Override
	public TemplateTreeDataProxy getData(String keyId) {
		Template template;
		List<Template> list = TemplateDataUtils.getAllTemplates();
		if (list.size() == 0) {
			template = TemplateGenerator.createTestTemplate();
			template = TemplateDataUtils.edit(template);
		} else {
			template = list.get(0);
		}
		TemplateTreeDataProxy proxy = template.asProxy();
		return proxy;
	}

}
