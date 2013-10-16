package ru.kmz.web.ordercommon.client;

import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface OrderProxyProperties extends PropertyAccess<OrderProxy> {
	@Path("id")
	ModelKeyProvider<OrderProxy> key();

	@Path("name")
	ValueProvider<OrderProxy, String> name();

	ValueProvider<OrderProxy, String> customer();

	ValueProvider<OrderProxy, String> legalNumber();

}