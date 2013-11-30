package ru.kmz.web.projects.client;

import java.util.Date;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.common.client.window.ProgressOperationMessageBoxUtils;
import ru.kmz.web.ganttcommon.client.GanttTaskContextMenuHandler;
import ru.kmz.web.ganttcommon.client.ProjectsGantt;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.client.window.SetProductNewDateSector;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;
import ru.kmz.web.projectscommon.client.ProjectsCommonModule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;

public class ProjectsModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable, IUpdatableWithValue<GanttData>,
		GanttTaskContextMenuHandler {

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
		return "Планирование";
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
		AutoProgressMessageBox box = ProgressOperationMessageBoxUtils.getServerRequest();
		box.show();
		GanttDataFilter filter = buttonsToolBar.getFilter();
		getService().getCurrentTasks(filter, new UpdateGanttData(box));
	}

	@Override
	public void update(GanttData ganttData) {
		if (gantt == null) {
			gantt = new ProjectsGantt(ganttData, this);
			container.add(gantt);
		} else {
			gantt.refreshAsNewData(ganttData);
		}
	}

	@Override
	public void setPersentDone(String id, int persents) {
		AutoProgressMessageBox box = ProgressOperationMessageBoxUtils.getServerOperation();
		box.show();
		ProjectsCommonModule.getService().setCompliteTaskPersents(id, persents, new UpdateAfteOperation(box, false));
	}

	@Override
	public void showNewDateSelector(final String id) {
		SetProductNewDateSector selector = new SetProductNewDateSector();
		selector.setUpdatable(new IUpdatableWithValue<Date>() {

			@Override
			public void update(Date value) {
				AutoProgressMessageBox box = ProgressOperationMessageBoxUtils.getServerOperation();
				box.show();
				getService().updateDate(id, value, new UpdateAfteOperation(box, true));
			}
		});
		selector.show();
	}

	@Override
	public void delete(String id) {
		AutoProgressMessageBox box = ProgressOperationMessageBoxUtils.getServerOperation();
		box.show();
		getService().deleteProduct(id, new UpdateAfteOperation(box, true));
	}

	public void createNewProduct(CalculatorInputDataProxy inputData) {
		AutoProgressMessageBox box = ProgressOperationMessageBoxUtils.getServerOperation();
		box.show();
		getService().save(inputData, new UpdateAfteOperation(box, true));
	}

	private class UpdateGanttData extends AsyncCallbackWithErrorMessage<GanttData> {

		private boolean calculate;

		public UpdateGanttData(Window window) {
			this(window, false);
		}

		public UpdateGanttData(Window window, boolean calculate) {
			super(window);
			this.calculate = calculate;
		}

		@Override
		public void onSuccess(GanttData result) {
			if (calculate) {
				Info.display("Расчет", "Выполнен расчет без сохранения, для сохранения выберите заказ");
			}
			update(result);
			window.hide();
		}

	}

	private class UpdateAfteOperation extends AsyncCallbackWithErrorMessage<Void> {
		private boolean reload;

		public UpdateAfteOperation(Window window, boolean reload) {
			super(window);
			this.reload = reload;
		}

		@Override
		public void onSuccess(Void result) {
			window.hide();
			Info.display("Операция завершена", "Изменения сохранены");
			if (reload) {
				update();
			} else {
				gantt.refresh();
			}
		}
	}

}
