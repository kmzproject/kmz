package ru.kmz.web.resources.client;

import java.util.List;

import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("resourcesModule")
public interface ResourcesModuleService extends RemoteService {

	List<ResourceProxy> getAllResources();

	ResourceProxy editResource(ResourceProxy resource);
}
