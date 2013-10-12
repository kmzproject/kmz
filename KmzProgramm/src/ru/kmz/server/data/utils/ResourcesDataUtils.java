package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.Resource;

public class ResourcesDataUtils {

	@SuppressWarnings("unchecked")
	public static List<Resource> getAllResources() {
		List<Resource> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(Resource.class);
			list = (List<Resource>) query.execute();
		} finally {
			em.close();
		}
		return list;

	}

	public static Resource edit(Resource resource) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			em.makePersistent(resource);
		} finally {
			em.close();
		}
		return resource;
	}

	public static void delete(long keyId) {
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Resource resource = em.getObjectById(Resource.class, keyId);
			em.deletePersistent(resource);
		} finally {
			em.close();
		}
	}

}
