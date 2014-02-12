package ru.kmz.web.projectscommon.client;

import java.util.Date;

import ru.kmz.web.projectscommon.shared.ProductionProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ProductionProxyProperties extends PropertyAccess<ProductionProxy> {
	@Path("id")
	ModelKeyProvider<ProductionProxy> key();

	@Path("name")
	ValueProvider<ProductionProxy, String> name();

	ValueProvider<ProductionProxy, String> orderNameAndCode();

	ValueProvider<ProductionProxy, Date> planStart();

	ValueProvider<ProductionProxy, Date> planFinish();

	ValueProvider<ProductionProxy, String> code();

	ValueProvider<ProductionProxy, String> taskState();

}