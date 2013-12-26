package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateDataUtils {

	public static Template edit(Template template) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			template = pm.makePersistent(template);
		} finally {
			pm.close();
		}
		return template;
	}

	public static ProductTemplateElement edit(ProductTemplateElement element) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(element);
		} finally {
			pm.close();
		}
		return element;
	}

	@SuppressWarnings("unchecked")
	public static List<Template> getAllTemplates() {
		List<Template> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(Template.class);
			query.setOrdering("name");
			list = (List<Template>) query.execute();
		} finally {
			em.close();
		}
		return list;
	}

	public static Template getTemplate(Long id) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Template template = pm.getObjectById(Template.class, id);
			loadAllChild(pm, template);
			return template;
		} finally {
			pm.close();
		}
	}

	public static ProductTemplateElement getProducteTemplateElement(Long id) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProductTemplateElement element = pm.getObjectById(ProductTemplateElement.class, id);
			loadAllChild(pm, element);
			return element;
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	// TODO: передалеть процесс загрузки на получения сначала списка, а уже
	// после загрузки
	private static void loadAllChild(PersistenceManager pm, ProductTemplateElement element) {
		Query query = pm.newQuery(ProductTemplateElement.class, " parentId == :parentKey");
		query.setOrdering("orderNum");
		List<ProductTemplateElement> list = (List<ProductTemplateElement>) query.execute(element.getId());
		for (ProductTemplateElement producteTemplateElement : list) {
			element.add(producteTemplateElement);
			loadAllChild(pm, producteTemplateElement);
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadAllChild(PersistenceManager pm, Template template) {
		Query q = pm.newQuery(ProductTemplateElement.class, " parentId == null && templateId == templateKey");
		q.declareParameters("long templateKey");
		List<ProductTemplateElement> list = (List<ProductTemplateElement>) q.execute(template.getId());
		if (list.size() == 1) {
			template.setRootElement(list.get(0));
			loadAllChild(pm, template.getRootElement());
		}
	}

	public static void deleteProductTemplateElement(Long key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProductTemplateElement element = pm.getObjectById(ProductTemplateElement.class, key);
			pm.deletePersistent(element);
		} finally {
			pm.close();
		}
	}

	public static void deleteTemplate(Long key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Template template = pm.getObjectById(Template.class, key);
			pm.deletePersistent(template);
		} finally {
			pm.close();
		}
	}

}