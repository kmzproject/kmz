package ru.kmz.web.template.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.generator.TemplateTestData;
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
			Template template = TemplateTestData.createTemplate();
			template = TemplateDataUtils.edit(template);
			templates = new ArrayList<Template>(1);
			templates.add(template);
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
		// ProducteTemplateElement element = new ProducteTemplateElement();
		// element.setName("Новый узел");
		// element.setDuration(0);
		// element.setResourceType(ResourceTypes.ORDER);
		// ProducteTemplate productTemplate =
		// TemplateDataUtils.getProducteTemplate(parentId);
		// if (productTemplate != null) {
		// productTemplate.add(element);
		// TemplateDataUtils.edit(productTemplate);
		// } else {
		// ProducteTemplateElement productTemplateElement =
		// TemplateDataUtils.getProducteTemplateElement(parentId);
		// productTemplateElement.add(element);
		// TemplateDataUtils.edit(productTemplateElement);
		// }
		// return element.asProxy();
		return null;
	}

	@Override
	public void deleteTemplateTreeNode(String key) {
		TemplateDataUtils.deleteProductTemplateElement(key);
	}

	@Override
	public TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy) {
		// ProducteTemplateElement element =
		// TemplateDataUtils.getProducteTemplateElement(proxy.getId());
		// if (element != null) {
		// element.updateData(proxy);
		// TemplateDataUtils.edit(element);
		// proxy = element.asProxy();
		// return proxy;
		// }
		return null;
	}

}
