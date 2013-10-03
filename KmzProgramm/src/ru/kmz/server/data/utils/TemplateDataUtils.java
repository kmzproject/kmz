package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ru.kmz.server.data.EMF;
import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateDataUtils {

	public static Template edit(Template template) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			if (template.getKey() == null) {
				em.persist(template);
			} else {
				em.merge(template);
			}
		} finally {
			em.close();
		}
		return template;
	}

	public static ProducteTemplateElement edit(ProducteTemplateElement element) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			if (element.getKey() == null) {
				em.persist(element);
			} else {
				em.merge(element);
			}
		} finally {
			em.close();
		}
		return element;
	}

	public static ProducteTemplate edit(ProducteTemplate producteTemplate) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			if (producteTemplate.getKey() == null) {
				em.persist(producteTemplate);
			} else {
				em.merge(producteTemplate);
			}
		} finally {
			em.close();
		}
		return producteTemplate;
	}

	@SuppressWarnings("unchecked")
	public static List<Template> getAllTemplates() {
		List<Template> list = null;
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			Query q = em.createQuery("select template from Template template ");
			list = new ArrayList<Template>(q.getResultList());
		} finally {
			em.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static Template getTemplate(int keyId) {
		List<Template> list = null;
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			Query q = em.createQuery("select t from Template t ");
			list = new ArrayList<Template>(q.getResultList());
		} finally {
			em.close();
		}
		for (Template template : list) {
			if (template.getKey().getId() == keyId)
				return template;
		}
		return null;
	}

	public static ProducteTemplate getProducteTemplate(long keyId) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			ProducteTemplate producteTemplate = em.find(ProducteTemplate.class, keyId);
			return producteTemplate;
		} finally {
			em.close();
		}
	}

	public static ProducteTemplateElement getProducteTemplateElement(long keyId) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			ProducteTemplateElement element = em.find(ProducteTemplateElement.class, keyId);
			return element;
		} finally {
			em.close();
		}
	}

	public static void deleteProductTemplateElement(long keyId) {
		ProducteTemplateElement element = getProducteTemplateElement(keyId);
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.remove(element);
		} finally {
			em.close();
		}
	}
}
