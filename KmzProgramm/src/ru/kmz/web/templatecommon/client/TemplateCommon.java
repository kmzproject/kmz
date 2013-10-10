package ru.kmz.web.templatecommon.client;

import com.google.gwt.core.client.GWT;

public class TemplateCommon {

	private final static TemplateCommonServiceAsync templateMpduleService = GWT.create(TemplateCommonService.class);

	public static TemplateCommonServiceAsync getService() {
		return templateMpduleService;
	}

}
