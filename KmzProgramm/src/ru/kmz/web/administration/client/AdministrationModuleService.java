package ru.kmz.web.administration.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("administrationModule")
public interface AdministrationModuleService extends RemoteService {

	void recreateData();

}
