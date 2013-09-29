package ru.kmz.web.resources.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.resources.client.ResourcesModuleService;
import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ResourcesModuleServiceImpl extends RemoteServiceServlet implements ResourcesModuleService {

	static List<ResourceProxy> list = new ArrayList<ResourceProxy>();

	@Override
	public List<ResourceProxy> getAllResources() {
		// List<ResourceProxy> list = new ArrayList<ResourceProxy>();
		if (list.size() == 0)
			list.add(new ResourceProxy("1", "name1", ResourceTypesConsts.ORDER));
		return list;
	}

	@Override
	public ResourceProxy editResource(ResourceProxy resource) {
		list.add(resource);
		return resource;
	}

}
