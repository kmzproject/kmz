package ru.kmz.web.projectscommon.client;

import java.util.Date;

import ru.kmz.web.projectscommon.shared.ProductElementTaskProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ProductElementTaskProxyProperties extends PropertyAccess<ProductElementTaskProxy> {
	@Path("id")
	ModelKeyProvider<ProductElementTaskProxy> key();

	@Path("name")
	ValueProvider<ProductElementTaskProxy, String> name();

	ValueProvider<ProductElementTaskProxy, String> orderName();

	ValueProvider<ProductElementTaskProxy, Date> planStart();

	ValueProvider<ProductElementTaskProxy, Date> planFinish();

	ValueProvider<ProductElementTaskProxy, String> code();
}