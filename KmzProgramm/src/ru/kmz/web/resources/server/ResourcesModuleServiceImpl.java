package ru.kmz.web.resources.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.utils.ResourcesDataUtils;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.web.resources.client.ResourcesModuleService;
import ru.kmz.web.resources.shared.ResourceProxy;

@SuppressWarnings("serial")
public class ResourcesModuleServiceImpl extends AbstractServiceImpl implements ResourcesModuleService {

	@Override
	public List<ResourceProxy> getAllResources() {
		List<ResourceProxy> result = new ArrayList<ResourceProxy>();
		List<Resource> list = ResourcesDataUtils.getAllResources();
		for (Resource resource : list) {
			result.add(resource.asProxy());
		}
		return result;
	}

	@Override
	public ResourceProxy editResource(ResourceProxy proxy) {
		Resource resource = new Resource(proxy);
		resource = ResourcesDataUtils.edit(resource);
		proxy = resource.asProxy();
		return proxy;
	}
}
