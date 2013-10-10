package ru.kmz.web.template.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.web.template.client.TemplateModuleService;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;

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
	public TemplateTreeDataProxy getData(String key) {
		Template template = TemplateDataUtils.getTemplate(key);
		TemplateTreeDataProxy proxy = template.asProxy();
		return proxy;
	}

	/***
	 * @param parentId
	 *            - id узла или шублона если узел верхнего уровня
	 */
	@Override
	public TemplateTreeNodeBaseProxy createNewTemplateTreeNode(String parentKey) {
		ProductTemplateElement parentElement = TemplateDataUtils.getProducteTemplateElement(parentKey);
		ProductTemplateElement element = new ProductTemplateElement("Новый узел", 0, ResourceTypes.ORDER,
				parentElement);
		element = TemplateDataUtils.edit(element);
		return element.asProxy();
	}

	@Override
	public void deleteTemplateTreeNode(String key) {
		TemplateDataUtils.deleteProductTemplateElement(key);
	}

	@Override
	public TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy) {
		ProductTemplateElement element = TemplateDataUtils.getProducteTemplateElement(proxy.getId());
		element.updateData(proxy);
		TemplateDataUtils.edit(element);
		proxy = element.asProxy();
		return proxy;
	}

}
