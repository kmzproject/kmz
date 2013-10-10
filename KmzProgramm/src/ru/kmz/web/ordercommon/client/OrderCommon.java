package ru.kmz.web.ordercommon.client;

import com.google.gwt.core.client.GWT;

public class OrderCommon {

	private final static OrderCommonServiceAsync service = GWT.create(OrderCommonService.class);

	public static OrderCommonServiceAsync getService() {
		return service;
	}

}
