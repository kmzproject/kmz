package ru.kmz.web.administration.client;

import ru.kmz.web.administration.shared.UserProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface UserProxyProperties extends PropertyAccess<UserProxy> {

	@Path("id")
	ModelKeyProvider<UserProxy> id();

	@Path("username")
	ValueProvider<UserProxy, String> username();

}
