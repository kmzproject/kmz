package ru.kmz.web.templatecommon.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.web.templatecommon.client.TemplateCommonService;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TemplateCommonServiceImpl extends RemoteServiceServlet implements TemplateCommonService {

	@Override
	public List<TemplateTreeDataProxy> getTemplateList() {
		List<Template> templates = TemplateDataUtils.getAllTemplates();
		List<TemplateTreeDataProxy> list = new ArrayList<TemplateTreeDataProxy>();
		for (Template template : templates) {
			list.add(template.asProxy());
		}
		return list;
	}

}
