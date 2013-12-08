package ru.kmz.web.administration.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.User;
import ru.kmz.server.data.utils.UserDataUtils;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.web.administration.client.AdministrationModuleService;
import ru.kmz.web.administration.shared.UserProxy;

@SuppressWarnings("serial")
public class AdministrationModuleServiceImpl extends AbstractServiceImpl implements AdministrationModuleService {

	@Override
	public List<UserProxy> getUsers() {
		List<UserProxy> list = new ArrayList<UserProxy>();
		List<User> users = UserDataUtils.getUsers();
		for (User user : users) {
			list.add(user.asProxy());
		}
		return list;
	}

	@Override
	public void setNewPasswork(String userName, String newPassword) {
		User user = UserDataUtils.getUser(userName);
		user.setPassword(newPassword);
		UserDataUtils.edit(user);
	}

	@Override
	public UserProxy editUser(UserProxy proxy) {
		User user = new User();
		user.setPassword("123456");
		user.setUsername(proxy.getUsername());
		UserDataUtils.edit(user);
		return user.asProxy();
	}
}
