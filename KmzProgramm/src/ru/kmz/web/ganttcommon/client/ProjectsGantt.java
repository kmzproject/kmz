package ru.kmz.web.ganttcommon.client;

import ru.kmz.web.ganttcommon.shared.GanttData;

public class ProjectsGantt extends CommonGanttContainer {

	public ProjectsGantt(GanttData data, GanttTaskContextMenuHandler handler) {
		super(data, handler);
		showPercentDone = true;
	}

}
