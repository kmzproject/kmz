package ru.kmz.server.data.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ru.kmz.server.data.EMF;
import ru.kmz.server.data.model.Resource;

public class ResourcesDataUtils {

	@SuppressWarnings("unchecked")
	public static List<Resource> getAllResources() {
		List<Resource> list = null;
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			Query q = em.createQuery("select resource from Resource resource");
			list = new ArrayList<Resource>(q.getResultList());
		} finally {
			em.close();
		}
		return list;

	}

	public static Resource edit(Resource resource) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			if (resource.getKey() == null) {
				em.persist(resource);
			} else {
				em.merge(resource);
			}
		} finally {
			em.close();
		}
		return resource;

	}
}
