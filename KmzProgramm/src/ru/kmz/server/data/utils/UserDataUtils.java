package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.User;

public class UserDataUtils {

	private static boolean hasAdmin;

	public static void checkAdmin() {
		if (hasAdmin)
			return;
		if (getUser("admin") == null) {
			User user = new User();
			user.setPassword("123456");
			user.setUsername("admin");
			edit(user);
		}
		hasAdmin = true;
	}

	public static User edit(User user) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public static User getUser(String userName) {
		List<User> list = null;
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(User.class, " username == :username");
			list = (List<User>) query.execute(userName);
		} finally {
			pm.close();
		}
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
}
