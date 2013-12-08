package ru.kmz.web.administration.client;

import java.util.List;

import ru.kmz.web.administration.shared.UserProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdministrationModuleServiceAsync {

	void getUsers(AsyncCallback<List<UserProxy>> callback);

	void setNewPasswork(String userName, String newPassword, AsyncCallback<Void> callback);

	void editUser(UserProxy proxy, AsyncCallback<UserProxy> callback);

}
