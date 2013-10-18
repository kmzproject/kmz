package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.client.ProjectsGantt;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ordercommon.client.window.OrderSelectWindow;
import ru.kmz.web.projects.client.window.SelectCalculationInfo;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class ProjectsModuleView extends AbstarctModuleView<VerticalPanel> implements IUpdatable, IUpdatableWithValue<CalculatorInputDataProxy> {

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

		HorizontalPanel buttonsPanel = new HorizontalPanel();

		TextButton selectCalculationDataButton = new TextButton("Добавить новое изделиe");
		selectCalculationDataButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				SelectCalculationInfo window = new SelectCalculationInfo();
				window.setUpdatable(ProjectsModuleView.this);
				window.show();

			}
		});
		buttonsPanel.add(selectCalculationDataButton);

		TextButton selectDataButton = new TextButton("Сохранить");
		selectDataButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				OrderSelectWindow window = new OrderSelectWindow();
				window.setUpdatable(new IUpdatableWithValue<KeyValueData>() {
					@Override
					public void update(KeyValueData value) {
						CalculatorInputDataProxy input = ProjectsModuleView.this.inputData;
						getService().save(input, value.getKey(), new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Info.display("Сохранены", "Успешно сохранил");
							}

							@Override
							public void onFailure(Throwable caught) {
								Info.display("Ошибка", "Ошибка загрузки" + caught);
							}
						});
					}
				});
				window.show();
			}
		});
		buttonsPanel.add(selectDataButton);

		container.add(buttonsPanel);

		gantContainer = new HorizontalPanel();
		container.add(gantContainer);

		updateGantt();
	}

	public static ProjectsModuleServiceAsync getService() {
		return service;
	}

	private void updateGantt() {
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
					if (gantt == null) {
						gantt = new ProjectsGantt(result);
						gantt.setUpdateListener(ProjectsModuleView.this);
						gantContainer.clear();
						gantContainer.add(gantt);
					} else {
						gantt.refreshData(result);
					}
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
	public void update() {
		updateGantt();
	}

	private CalculatorInputDataProxy inputData;

	@Override
	public void update(final CalculatorInputDataProxy value) {
		final AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере", "Это может занять некоторое время");
		box.setProgressText("Обработка...");
		box.auto();
		box.show();

		getService().getGantResultData(value, new AsyncCallback<GanttData>() {
			@Override
			public void onSuccess(GanttData result) {
				if (result.getError() != null) {
					Info.display("Error", "Ошибка при обработке " + result.getError());
				} else {
					gantt.refreshData(result);
				}
				ProjectsModuleView.this.inputData = value;
				box.hide();
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "This is error " + caught);
				box.hide();
			}
		});
	}
	/*
	 * CalculatorInputDataProxy input = getInput();
	 * CalculatorModuleView.getService().save(input, value.getKey(), new
	 * AsyncCallback<Void>() {
	 * 
	 * @Override public void onSuccess(Void result) { Info.display("Сохранены",
	 * "Успешно сохранил"); }
	 * 
	 * @Override public void onFailure(Throwable caught) {
	 * Info.display("Ошибка", "Ошибка загрузки" + caught); } });
	 * 
	 * addButton = new TextButton("Сохранить"); addButton.setEnabled(false);
	 * addButton.addSelectHandler(new SelectHandler() {
	 * 
	 * @Override public void onSelect(SelectEvent event) { OrderSelectWindow
	 * window = new OrderSelectWindow(); window.setUpdatable(new
	 * UpdatebleOnSave()); window.show(); } });
	 */
}
