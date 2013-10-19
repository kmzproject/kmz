package ru.kmz.web.ganttcommon.client;

import ru.kmz.web.common.client.TreeIconProvider;
import ru.kmz.web.ganttcommon.client.data.Dependency;
import ru.kmz.web.ganttcommon.client.data.Task;

import com.gantt.client.Gantt;
import com.gantt.client.config.GanttConfig;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CommonGantt extends Gantt<Task, Dependency> {

	public CommonGantt(TreeStore<Task> taskStore, ListStore<Dependency> dependecyStore, GanttConfig config) {
		super(taskStore, dependecyStore, config);
		getGanttPanel().getView().setPreventScrollToTopOnRefresh(true);
		// setMenu();
		TreeGrid<Task> t =((TreeGrid<Task>)getLeftGrid());
		t.setIconProvider(new TreeIconProvider<Task>());
	}
	
	private Menu setMenu(){
		Menu contextMenu = new Menu();
		contextMenu.setWidth(140);
		MenuItem insert = new MenuItem();  
		insert.setText("Insert Item");  
		contextMenu.add(insert);  
		
		return contextMenu;
	}

}
