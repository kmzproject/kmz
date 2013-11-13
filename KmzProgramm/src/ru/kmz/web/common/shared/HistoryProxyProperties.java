package ru.kmz.web.common.shared;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface HistoryProxyProperties extends PropertyAccess<HistoryProxy> {
	@Path("id")
	ModelKeyProvider<HistoryProxy> key();

	@Path("name")
	ValueProvider<HistoryProxy, String> name();

	ValueProvider<HistoryProxy, String> comment();

	ValueProvider<HistoryProxy, Date> date();

	ValueProvider<HistoryProxy, String> user();

}