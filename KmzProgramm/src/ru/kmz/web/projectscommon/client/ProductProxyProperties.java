package ru.kmz.web.projectscommon.client;

import java.util.Date;

import ru.kmz.web.projectscommon.shared.ProductProxy;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ProductProxyProperties extends PropertyAccess<ProductProxy> {
	@Path("id")
	ModelKeyProvider<ProductProxy> key();

	@Path("name")
	ValueProvider<ProductProxy, String> name();

	ValueProvider<ProductProxy, String> orderNameAndCode();

	ValueProvider<ProductProxy, Date> planStart();

	ValueProvider<ProductProxy, Date> planFinish();

	ValueProvider<ProductProxy, String> code();

	ValueProvider<PurchaseProxy, String> taskState();

}