package ru.kmz.web.common.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.common.client.service.TemplateCommonService;
import ru.kmz.web.common.shared.TemplateTreeDataProxy;
import ru.kmz.web.common.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.client.TemplateModuleService;

@SuppressWarnings("serial")
public class TemplateCommonServiceImpl extends AbstractServiceImpl implements TemplateCommonService, TemplateModuleService {

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
	public TemplateTreeDataProxy getData(long key) {
		Template template = TemplateDataUtils.getTemplate(key);
		TemplateTreeDataProxy proxy = template.asProxy();
		return proxy;
	}

	/***
	 * @param parentId
	 *            - id узла или шублона если узел верхнего уровня
	 */
	@Override
	public TemplateTreeNodeBaseProxy createNewTemplateTreeNode(long parentKey) {
		ProductTemplateElement parentElement = TemplateDataUtils.getProducteTemplateElement(parentKey);
		ProductTemplateElement element = new ProductTemplateElement("Новый узел", 0, ResourceTypes.PURCHASE, parentElement);
		element = TemplateDataUtils.edit(element);
		return element.asProxy();
	}

	@Override
	public void deleteTemplateTreeNode(long key) {
		TemplateDataUtils.deleteProductTemplateElement(key);
	}

	@Override
	public TemplateTreeNodeBaseProxy save(TemplateTreeNodeBaseProxy proxy) {
		ProductTemplateElement element = TemplateDataUtils.getProducteTemplateElement(proxy.getId());
		element.updateData(proxy);
		TemplateDataUtils.edit(element);
		proxy = element.asProxy();
		HistoryUtils.editTemplate(element.getTemplateId());
		return proxy;
	}

	@Override
	public void editTemplate(TemplateTreeDataProxy proxy) {
		if (proxy.getId() != 0) {
			Template template = TemplateDataUtils.getTemplate(proxy.getId());
			template.setName(proxy.getName());
			ProductTemplateElement rootElement = template.getRootElement();
			rootElement.setName(proxy.getName());
			TemplateDataUtils.edit(template);
			TemplateDataUtils.edit(rootElement);
			HistoryUtils.editTemplate(template);
		} else {
			Template template = TemplateDataUtils.edit(new Template(proxy.getName()));
			TemplateDataUtils.edit(new ProductTemplateElement(proxy.getName(), template));
			HistoryUtils.createTemplate(template);
		}

	}

	@Override
	public void getDeleteTemplate(long key) {
		TemplateDataUtils.deleteTemplate(key);
	}

}
