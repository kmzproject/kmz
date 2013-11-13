package ru.kmz.web.common.client;

import ru.kmz.web.common.client.service.CommonService;
import ru.kmz.web.common.client.service.CommonServiceAsync;

import com.google.gwt.core.client.GWT;

public class CommonModule {

	private final static CommonServiceAsync service = GWT.create(CommonService.class);

	public static CommonServiceAsync getService() {
		return service;
	}

}
