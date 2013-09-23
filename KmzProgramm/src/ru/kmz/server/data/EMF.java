package ru.kmz.server.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static EntityManagerFactory emfInstance = null;

	private EMF() {
	}

	public static EntityManagerFactory get() {
		if (emfInstance == null) {
			emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
		}
		return emfInstance;
	}
}