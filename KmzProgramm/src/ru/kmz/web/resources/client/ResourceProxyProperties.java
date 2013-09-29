package ru.kmz.web.resources.client;

import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ResourceProxyProperties extends PropertyAccess<ResourceProxy> {
	@Path("id")
	ModelKeyProvider<ResourceProxy> key();

	@Path("name")
	ValueProvider<ResourceProxy, String> name();

	ValueProvider<ResourceProxy, String> resourceType();

}