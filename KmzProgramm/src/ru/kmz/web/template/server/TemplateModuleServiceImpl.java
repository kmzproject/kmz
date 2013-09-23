package ru.kmz.web.template.server;

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
	public TemplateTreeDataProxy getData() {
		try {
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
		} catch (Exception ex) {
			System.out.println(ex);
		}
		TemplateTreeDataProxy data = new TemplateTreeDataProxy(TemplateTreeDataExample.getRoot());
		return data;
	}

}
