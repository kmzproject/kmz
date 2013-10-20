package ru.kmz.web.ganttcommon.client;

import ru.kmz.web.common.client.TreeIconProvider;
import ru.kmz.web.ganttcommon.client.data.Dependency;
import ru.kmz.web.ganttcommon.client.data.Task;

import com.gantt.client.Gantt;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CommonGantt extends Gantt<Task, Dependency> {

	public CommonGantt(TreeStore<Task> taskStore, ListStore<Dependency> dependecyStore, CommonGanttConfig config) {
		super(taskStore, dependecyStore, config);
		
		getGanttPanel().getView().setPreventScrollToTopOnRefresh(true);

		TreeGrid<Task> t = ((TreeGrid<Task>) getLeftGrid());
		t.setIconProvider(new TreeIconProvider<Task>());
		
		this.addTaskContextMenuHandler(config.getTaskContextMenu());
	}
	
	public void setContextMenuHandler(GanttTaskContextMenuHandler handler){
		((CommonGanttConfig)this.config).getTaskContextMenu().setHandler(handler);
	}
}
