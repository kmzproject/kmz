package ru.kmz.web.purchases.client;

import java.util.Date;

import ru.kmz.web.purchases.shared.PurchaseProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PurchaseProxyProperties extends PropertyAccess<PurchaseProxy> {
	@Path("id")
	ModelKeyProvider<PurchaseProxy> key();

	@Path("name")
	ValueProvider<PurchaseProxy, String> name();

	ValueProvider<PurchaseProxy, String> orderName();

	ValueProvider<PurchaseProxy, Date> planStart();

	ValueProvider<PurchaseProxy, Date> planFinish();

}