package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.client.ProjectsGantt;
import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;

public class ProjectsModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable, IUpdatableWithValue<GanttData> {

	private final static ProjectsModuleServiceAsync service = GWT.create(ProjectsModuleService.class);

	private static ProjectsModuleView instanse;
	private ProjectsToolBar buttonsToolBar;
	private ProjectsGantt gantt;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Производство";
	}

	public static ProjectsModuleView getInstance() {
		if (instanse == null)
			instanse = new ProjectsModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		buttonsToolBar = new ProjectsToolBar(this);

		container.add(buttonsToolBar);

		update();
	}

	public ProjectsGantt getGantt() {
		return gantt;
	}

	public static ProjectsModuleServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		final AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере", "Это может занять некоторое время");
		box.setProgressText("Обработка...");
		box.auto();
		box.show();

		getService().getCurrentTasks(new AsyncCallback<GanttData>() {

			@Override
			public void onSuccess(GanttData result) {
				if (result.getError() != null) {
					Info.display("Error", "Ошибка при обработке " + result.getError());
				} else {
					update(result);
				}
				box.hide();
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "This is error " + caught);
				box.hide();
			}
		});
	}

	@Override
	public void update(GanttData ganttData) {
		if (gantt == null) {
			gantt = new ProjectsGantt(ganttData);
			container.add(gantt);
		} else {
			gantt.refreshData(ganttData);
		}
	}

}
