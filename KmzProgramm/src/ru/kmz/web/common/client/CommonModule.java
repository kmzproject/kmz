package ru.kmz.web.common.client;

import ru.kmz.web.common.client.service.CommonService;
import ru.kmz.web.common.client.service.CommonServiceAsync;
import ru.kmz.web.common.client.service.TemplateCommonService;
import ru.kmz.web.common.client.service.TemplateCommonServiceAsync;

import com.google.gwt.core.client.GWT;

public class CommonModule {

	private final static CommonServiceAsync service = GWT.create(CommonService.class);
	private final static TemplateCommonServiceAsync templateMpduleService = GWT.create(TemplateCommonService.class);

	public static CommonServiceAsync getService() {
		return service;
	}

	public static TemplateCommonServiceAsync getTemplateService() {
		return templateMpduleService;
	}

}
