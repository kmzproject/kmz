package ru.kmz.web.production.client;

import java.util.List;

import ru.kmz.web.projectscommon.shared.ProductionProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsCommon")
public interface ProductionModuleService extends RemoteService {

	void compliteProduction(String id);

	List<ProductionProxy> getActiveProductions();

}
