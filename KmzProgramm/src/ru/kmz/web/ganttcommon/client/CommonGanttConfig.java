package ru.kmz.web.ganttcommon.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.ganttcommon.client.data.DependencyProps;
import ru.kmz.web.ganttcommon.client.data.Task;
import ru.kmz.web.ganttcommon.client.data.TaskProps;

import com.gantt.client.config.GanttConfig;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.scheduler.client.core.TimeResolution.Unit;
import com.scheduler.client.core.timeaxis.TimeAxisGenerator;
import com.scheduler.client.zone.ZoneGeneratorInt;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class CommonGanttConfig extends GanttConfig {

	public void setLeftColumns(ColumnModel<Task> columnModul) {
		this.leftColumns = columnModul;
	}

	public void setTimeHeaderConfig(ArrayList<TimeAxisGenerator> headers) {
		this.timeHeaderConfig = headers;
	}

	public void setZoneGenerators(List<ZoneGeneratorInt> zones) {
		this.zoneGenerators = zones;
	}

	public CommonGanttConfig() {
		// Enable task resize
		this.resizeHandle = ResizeHandle.NONE;
		// Enable dependency DnD
		this.dependencyDnDEnabled = false;
		// Only EndToStart allowed
		// config.dependencyDnDTypes = DependencyDnDConstants.ENDtoSTART ;

		// Enable task DnD
		this.taskDnDEnabled = false;
		// Define "snap to" resolution
		this.timeResolutionUnit = Unit.DAY;
		this.timeResolutionIncrement = 1;
		// Define the DateFormat of the tooltips
		this.tipDateFormat = DateTimeFormat.getFormat("MMM d");
		// Disable time toolTip
		this.timeTipEnabled = false;
		// Defines if the timeAxis columns should be autosized to fit.
		this.fitColumns = false;
		// Define column width
		this.columnWidth = 40;
		// Enable task contextMenu
		this.taskContextMenuEnabled = true;
		// Enable dependency contextMenu
		this.dependencyContextMenuEnabled = false;
		//
		this.taskContextMenu = new GanttTaskContextMenu();

		this.taskProperties = TaskProps.props;
		this.dependencyProperties = DependencyProps.depProps;

		// Cascade Changes
		this.cascadeChanges = false;
	}

	public GanttTaskContextMenu getTaskContextMenu() {
		return (GanttTaskContextMenu) this.taskContextMenu;
	}

}
