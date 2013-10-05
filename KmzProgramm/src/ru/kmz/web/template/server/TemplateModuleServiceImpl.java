package ru.kmz.web.template.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.ProducteTemplateElement;
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
		if (templates.size() == 0) {
			templates = new ArrayList<Template>(2);
			templates.add(TemplateTestData.createTemplate());
			templates.add(TemplateTestData.createTemplateShort2());
		}
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
		// ProducteTemplateElement element = new
		// ProducteTemplateElement("Новый узел", 0, ResourceTypes.ORDER);
		// ProducteTemplateElement productTemplateElement =
		// TemplateDataUtils.getProducteTemplateElement(parentKey);
		// productTemplateElement.add(element);
		// productTemplateElement =
		// TemplateDataUtils.edit(productTemplateElement);
		// element = productTemplateElement.getLastChild();
		// return element.asProxy();
		return null;
	}

	@Override
	public void deleteTemplateTreeNode(String key) {
		TemplateDataUtils.deleteProductTemplateElement(key);
	}

	@Override
	public TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy) {
		ProducteTemplateElement element = TemplateDataUtils.getProducteTemplateElement(proxy.getId());
		element.updateData(proxy);
		TemplateDataUtils.edit(element);
		proxy = element.asProxy();
		return proxy;
	}

}
