package ru.kmz.web.common.client.data;

import com.google.gwt.core.client.GWT;

public interface KeyStringValueDataProperties extends KeyValueDataProperties<String> {
	public static KeyStringValueDataProperties prop = GWT.create(KeyStringValueDataProperties.class);
}