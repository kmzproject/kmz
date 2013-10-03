package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateDataUtils {

	public static Template edit(Template template) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			template = em.makePersistent(template);
		} finally {
			em.close();
		}
		return template;
	}

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

	public static Template getTemplate(int keyId) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Template template = em.getObjectById(Template.class, keyId);
			return template;
		} finally {
			em.close();
		}
	}

	public static ProducteTemplateElement getProducteTemplateElement(long keyId) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			ProducteTemplateElement element = em.getObjectById(ProducteTemplateElement.class, keyId);
			return element;
		} finally {
			em.close();
		}
	}

	public static void deleteProductTemplateElement(long keyId) {
		ProducteTemplateElement element = getProducteTemplateElement(keyId);
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			em.deletePersistent(element);
		} finally {
			em.close();
		}
	}
}
