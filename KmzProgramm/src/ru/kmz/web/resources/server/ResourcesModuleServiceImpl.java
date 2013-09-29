package ru.kmz.web.resources.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.resources.client.ResourcesModuleService;
import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ResourcesModuleServiceImpl extends RemoteServiceServlet implements ResourcesModuleService {

	@Override
	public List<ResourceProxy> getAllResources() {
		List<ResourceProxy> list = new ArrayList<ResourceProxy>();
		list.add(new ResourceProxy("1", "name1", ResourceTypesConsts.ORDER));
		return list;
	}

	@Override
	public ResourceProxy editResource(ResourceProxy resource) {
		return resource;
	}

}
