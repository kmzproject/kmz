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
			edit(pm, template.getRootElement());
		} finally {
			pm.close();
		}
		return template;
	}

	public static ProducteTemplateElement edit(PersistenceManager pm, ProducteTemplateElement element) {
		element = pm.makePersistent(element);
		if (element.getChilds() != null) {
			for (ProducteTemplateElement e : element.getChilds()) {
				edit(pm, e);
			}
		}
		return element;
	}

	//
	// public static <T> T edit(PersistenceManager em, T element) {
	// return em.makePersistent(element);
	// }

	public static ProducteTemplateElement edit(ProducteTemplateElement element) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			element = em.makePersistent(element);
		} finally {
			em.close();
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
			list = (List<Template>) query.execute();
		} finally {
			em.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<ProducteTemplateElement> getAllElements() {
		List<ProducteTemplateElement> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(ProducteTemplateElement.class);
			list = (List<ProducteTemplateElement>) query.execute();
		} finally {
			em.close();
		}
		return list;
	}

	public static Template getTemplate(String key) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Template template = em.getObjectById(Template.class, key);
			return template;
		} finally {
			em.close();
		}
	}

	public static ProducteTemplateElement getProducteTemplateElement(String key) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			ProducteTemplateElement element = em.getObjectById(ProducteTemplateElement.class, key);
			return element;
		} finally {
			em.close();
		}
	}

	public static void deleteProductTemplateElement(String key) {
		ProducteTemplateElement element = getProducteTemplateElement(key);
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			em.deletePersistent(element);
		} finally {
			em.close();
		}
	}
}
