package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.client.ProjectsGantt;
import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.info.Info;

public class ProjectsModuleView extends AbstarctModuleView<VerticalPanel> implements IUpdatable, IUpdatableWithValue<GanttData> {

	private final static ProjectsModuleServiceAsync service = GWT.create(ProjectsModuleService.class);

	private static ProjectsModuleView instanse;
	private HorizontalPanel gantContainer;
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
		container = new VerticalPanel();
		container.setSpacing(10);

		CalculateSaveButtons buttonsPanel = new CalculateSaveButtons(this);

		container.add(buttonsPanel);

		gantContainer = new HorizontalPanel();
		container.add(gantContainer);

		update();
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
		if (ganttData == null) {
			update();
			return;
		}
		if (gantt == null) {
			gantt = new ProjectsGantt(ganttData);
			gantt.setUpdateListener(ProjectsModuleView.this);
			gantContainer.clear();
			gantContainer.add(gantt);
		} else {
			gantt.refreshData(ganttData);
		}
	}

}
