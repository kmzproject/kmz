package ru.kmz.web.projectscommon.client;

import com.google.gwt.core.client.GWT;

public class ProjectsCommonModule {

	private final static ProjectsCommonServiceAsync service = GWT.create(ProjectsCommonService.class);

	public static ProjectsCommonServiceAsync getService() {
		return service;
	}

}
