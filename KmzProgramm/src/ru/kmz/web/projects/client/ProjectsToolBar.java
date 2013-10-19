package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;
import ru.kmz.web.ordercommon.client.window.OrderSelectWindow;
import ru.kmz.web.projects.client.window.SelectCalculationInfo;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProjectsToolBar implements IsWidget {

	private ToolBar toolBar;
	private CalculatorInputDataProxy inputData;
	private ProjectsModuleView projectModulesView;
	private ComboBox<KeyValueData> scalaCombo;

	public ProjectsToolBar(ProjectsModuleView projectModulesView) {
		this.projectModulesView = projectModulesView;
	}

	@Override
	public Widget asWidget() {
		if (toolBar == null) {
			createButtonPanel();
		}
		return toolBar;
	}

	private void createButtonPanel() {
		toolBar = new ToolBar();

		TextButton selectCalculationDataButton = new TextButton("Добавить новое изделиe");
		selectCalculationDataButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				SelectCalculationInfo window = new SelectCalculationInfo();
				window.setUpdatable(new IUpdatableWithValue<CalculatorInputDataProxy>() {

					@Override
					public void update(final CalculatorInputDataProxy value) {
						final AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере", "Это может занять некоторое время");
						box.setProgressText("Обработка...");
						box.auto();
						box.show();
						ProjectsModuleView.getService().getGantResultData(value, new AsyncCallbackWithErrorMessage<GanttData>() {
							@Override
							public void onSuccess(GanttData result) {
								if (result.getError() != null) {
									Info.display("Error", "Ошибка при обработке " + result.getError());
								} else {
									projectModulesView.update(result);
								}
								ProjectsToolBar.this.inputData = value;
								box.hide();
							}
						});
					}
				});
				window.show();

			}
		});
		toolBar.add(selectCalculationDataButton);

		TextButton selectDataButton = new TextButton("Сохранить");
		selectDataButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				OrderSelectWindow window = new OrderSelectWindow();
				window.setUpdatable(new IUpdatableWithValue<KeyValueData>() {
					@Override
					public void update(KeyValueData value) {
						ProjectsModuleView.getService().save(ProjectsToolBar.this.inputData, value.getKey(), new AsyncCallbackWithErrorMessage<Void>() {
							@Override
							public void onSuccess(Void result) {
								Info.display("Сохранены", "Успешно сохранил");
								projectModulesView.update();
							}
						});
					}
				});
				window.show();
			}
		});
		toolBar.add(selectDataButton);

		TextButton showAll = new TextButton("Раскрыть все");
		showAll.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				projectModulesView.getGantt().expandAll();
			}
		});
		toolBar.add(showAll);

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				projectModulesView.update();
			}
		});

		toolBar.add(refreshButton);

		toolBar.add(new FieldLabel(getCombobox(), "Масштаб"));

	}

	private ComboBox<KeyValueData> getCombobox() {
		ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
		list.add(new KeyValueData(ScaleConstants.DAY, "День"));
		list.add(new KeyValueData(ScaleConstants.WEEK, "Неделя"));
		list.add(new KeyValueData(ScaleConstants.MONTH, "Месяц"));

		scalaCombo = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		scalaCombo.setForceSelection(true);
		scalaCombo.setTypeAhead(true);
		scalaCombo.setTriggerAction(TriggerAction.ALL);
		scalaCombo.setEditable(false);
		scalaCombo.setValue(list.get(1));

		scalaCombo.addSelectionHandler(new SelectionHandler<KeyValueData>() {

			@Override
			public void onSelection(SelectionEvent<KeyValueData> event) {
				projectModulesView.getGantt().changeScale(event.getSelectedItem().getKey());
			}
		});

		return scalaCombo;
	}

}
