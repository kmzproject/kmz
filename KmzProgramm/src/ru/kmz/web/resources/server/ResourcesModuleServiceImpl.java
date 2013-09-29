package ru.kmz.web.resources.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.utils.ResourcesDataUtils;
import ru.kmz.web.resources.client.ResourcesModuleService;
import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ResourcesModuleServiceImpl extends RemoteServiceServlet implements ResourcesModuleService {

	private List<ResourceProxy> cachList = new ArrayList<ResourceProxy>();

	@Override
	public List<ResourceProxy> getAllResources() {
		if (cachList.size() == 0) {
			List<Resource> list = ResourcesDataUtils.getAllResources();
			for (Resource resource : list) {
				cachList.add(resource.asProxy());
			}
		}
		return cachList;
	}

	@Override
	public ResourceProxy editResource(ResourceProxy proxy) {
		Resource resource = new Resource(proxy);
		resource = ResourcesDataUtils.edit(resource);
		proxy = resource.asProxy();
		int index;
		for (index = 0; index < cachList.size(); index++) {
			if (proxy.getId() == cachList.get(index).getId())
				break;
		}
		if (index == cachList.size())
			cachList.add(proxy);
		else
			cachList.set(index, proxy);
		return resource.asProxy();
	}
}
