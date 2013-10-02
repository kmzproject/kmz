package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ru.kmz.server.data.EMF;
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
	public static Template getTemplates(int keyId) {
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
}
