package ru.kmz.web.administration.client;

import java.util.List;

import ru.kmz.web.administration.shared.UserProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("administrationModule.rpc")
public interface AdministrationModuleService extends RemoteService {

	List<UserProxy> getUsers();

	void setNewPasswork(String userName, String newPassword);

	UserProxy editUser(UserProxy proxy);
}
