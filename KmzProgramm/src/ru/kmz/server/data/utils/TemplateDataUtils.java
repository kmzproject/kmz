package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.ProducteTemplateElement;
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

	public static ProducteTemplateElement edit(ProducteTemplateElement element) {
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
			list.size();
		} finally {
			em.close();
		}
		return list;
	}

	public static Template getTemplate(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Template template = pm.getObjectById(Template.class, key);
			loadAllChild(pm, template);
			return template;
		} finally {
			pm.close();
		}
	}

	public static ProducteTemplateElement getProducteTemplateElement(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProducteTemplateElement element = pm.getObjectById(ProducteTemplateElement.class, key);
			loadAllChild(pm, element);
			return element;
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	// TODO: передалеть процесс загрузки на получения сначала списка, а уже
	// после загрузки
	private static void loadAllChild(PersistenceManager pm, ProducteTemplateElement element) {
		Query query = pm.newQuery(ProducteTemplateElement.class, " parentId == :parentKey");
		query.setOrdering("key");
		List<ProducteTemplateElement> list = (List<ProducteTemplateElement>) query.execute(element.getKey());
		for (ProducteTemplateElement producteTemplateElement : list) {
			element.add(producteTemplateElement);
			loadAllChild(pm, producteTemplateElement);
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadAllChild(PersistenceManager pm, Template template) {
		Query q = pm.newQuery(ProducteTemplateElement.class, " parentId == null && templateId == :templateKey");
		List<ProducteTemplateElement> list = (List<ProducteTemplateElement>) q.execute(template.getKey());
		template.setRootElement(list.get(0));
		loadAllChild(pm, template.getRootElement());
	}

	public static void deleteProductTemplateElement(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProducteTemplateElement element = pm.getObjectById(ProducteTemplateElement.class, key);
			pm.deletePersistent(element);
		} finally {
			pm.close();
		}
	}
}