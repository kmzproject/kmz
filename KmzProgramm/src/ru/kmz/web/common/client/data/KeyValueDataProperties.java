package ru.kmz.web.common.client.data;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

interface KeyValueDataProperties<T> extends PropertyAccess<KeyValueData<T>> {

	@Path("key")
	ModelKeyProvider<KeyValueData<T>> key();

	@Path("value")
	LabelProvider<KeyValueData<T>> value();

}