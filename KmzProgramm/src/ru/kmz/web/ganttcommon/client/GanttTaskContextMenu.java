package ru.kmz.web.ganttcommon.client;

import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.client.data.Task;

import com.gantt.client.config.GanttConfig.TaskType;
import com.gantt.client.event.TaskContextMenuEvent;
import com.gantt.client.event.TaskContextMenuEvent.TaskContextMenuHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class GanttTaskContextMenu extends Menu implements TaskContextMenuHandler<Task> {
	protected Task taskModel;

	private MenuItem setCompliteMenuItem;
	private MenuItem setNotCompliteMenuItem;
	private MenuItem setNewDateMenuItem;
	private MenuItem deleteMenuItem;
	private GanttTaskContextMenuHandler handler;

	public GanttTaskContextMenu() {
		setCompliteMenuItem = new MenuItem("Отметить как выполненную");
		setCompliteMenuItem.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				if (!isCompliteChildrens(taskModel)) {
					Info.display("Предупреждение", "Невозможность завершить выполнение задачи, пока есть не выполненные под задачи.");
					return;
				}
				setTaskComplitePersents(100);
			}
		});
		add(setCompliteMenuItem);

		setNotCompliteMenuItem = new MenuItem("Отметить как не выполненную");
		setNotCompliteMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				setTaskComplitePersents(0);
			}
		});
		add(setNotCompliteMenuItem);

		setNewDateMenuItem = new MenuItem("Изменить даты");
		setNewDateMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				handler.showNewDateSelector(taskModel.getIdLong());
			}
		});
		add(setNewDateMenuItem);

		deleteMenuItem = new MenuItem("Удалить");
		deleteMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				handler.delete(taskModel.getIdLong());
			}
		});
		add(deleteMenuItem);
	}

	private boolean isCompliteChildrens(Task task) {
		for (Task child : task.getChildren()) {
			if (!child.isComplite())
				return false;
			isCompliteChildrens(child);
		}
		return true;
	}

	private void setTaskComplitePersents(int persents) {
		handler.setPersentDone(taskModel.getIdLong(), persents);
		taskModel.setPercentDone(persents);
	}

	public void setHandler(GanttTaskContextMenuHandler handler) {
		this.handler = handler;
	}

	@Override
	public void onTaskContextMenu(TaskContextMenuEvent<Task> event) {
		event.getEvent().stopPropagation();
		this.taskModel = event.getTask();
		this.setPagePosition(event.getEvent().getClientX(), event.getEvent().getClientY());
		updateItems();
		this.show();
	}

	private void updateItems() {
		if (taskModel.getResourceType().equals(ResourceTypesConsts.PRODUCT)) {
			setNewDateMenuItem.setEnabled(true);
			deleteMenuItem.setEnabled(true);
		} else {
			setNewDateMenuItem.setEnabled(false);
			deleteMenuItem.setEnabled(false);
		}

		if (taskModel.getTaskType() == TaskType.PARENT) {
			setCompliteMenuItem.setEnabled(false);
			setNotCompliteMenuItem.setEnabled(false);
		} else {
			boolean isComplite = taskModel.isComplite();
			setCompliteMenuItem.setEnabled(!isComplite);
			setNotCompliteMenuItem.setEnabled(isComplite);
		}
	}
}
