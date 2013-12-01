package ru.kmz.web.production.client;

import java.util.List;

import ru.kmz.web.projectscommon.client.ProjectsCommonService;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.ProductionProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsCommon.rpc")
public interface ProductionModuleService extends RemoteService, ProjectsCommonService {

	void compliteProduction(long id);

	List<ProductionProxy> getActiveProductions(ProductElementTaskGridFilter filter);

}
