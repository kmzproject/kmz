package ru.kmz.web.common.client.data;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface KeyValueDataProperties extends PropertyAccess<KeyValueData> {

	KeyValueDataProperties prop = GWT.create(KeyValueDataProperties.class);

	@Path("key")
	ModelKeyProvider<KeyValueData> key();

	@Path("value")
	LabelProvider<KeyValueData> value();

}