package ru.kmz.web.products.client;

import java.util.Date;

import ru.kmz.web.projectscommon.shared.ProductProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ProductProxyProperties extends PropertyAccess<ProductProxy> {
	@Path("id")
	ModelKeyProvider<ProductProxy> key();

	@Path("name")
	ValueProvider<ProductProxy, String> name();

	ValueProvider<ProductProxy, String> orderName();

	ValueProvider<ProductProxy, Date> planStart();

	ValueProvider<ProductProxy, Date> planFinish();

	ValueProvider<ProductProxy, String> code();
}