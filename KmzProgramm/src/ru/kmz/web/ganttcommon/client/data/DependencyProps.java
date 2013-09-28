package ru.kmz.web.ganttcommon.client.data;

import com.gantt.client.model.DependencyProperties;
import com.google.gwt.core.client.GWT;

public interface DependencyProps extends DependencyProperties<Dependency> {
	public static final DependencyProps depProps = GWT.create(DependencyProps.class);

}
