package ru.kmz.web.templatecommon.client;

import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TemplateTreeDataProxyProperties extends PropertyAccess<TemplateTreeDataProxy> {
	@Path("id")
	ModelKeyProvider<TemplateTreeDataProxy> key();

	@Path("name")
	ValueProvider<TemplateTreeDataProxy, String> name();

}