package ru.kmz.web.common.client.data;

import com.google.gwt.core.client.GWT;

public interface KeyLongValueDataProperties extends KeyValueDataProperties<Long> {
	public static KeyLongValueDataProperties prop = GWT.create(KeyLongValueDataProperties.class);

}
