package ru.kmz.web.ganttcommon.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface CommonGanttResources extends ClientBundle {
	final CommonGanttResources resources = GWT.create(CommonGanttResources.class);

	@Source({ "Gantt.css" })
	GanttExampleStyle css();

	interface GanttExampleStyle extends CssResource {
		String todayLineMain();
	}

}