package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;
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
	private ProjectsModuleView projectModulesView;

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
					public void update(final CalculatorInputDataProxy inputData) {
						if (inputData.getOrderId() == null) {
							final AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере", "Это может занять некоторое время");
							box.setProgressText("Обработка...");
							box.auto();
							box.show();
							ProjectsModuleView.getService().getGantResultData(inputData, new AsyncCallbackWithErrorMessage<GanttData>() {
								@Override
								public void onSuccess(GanttData result) {
									if (result.getError() != null) {
										Info.display("Error", "Ошибка при обработке " + result.getError());
									} else {
										projectModulesView.update(result);
										Info.display("Расчет", "Выполнен расчет без сохранения, для сохранения выберите заказ");
									}
									box.hide();
								}
							});
						} else {
							ProjectsModuleView.getService().save(inputData, new AsyncCallbackWithErrorMessage<Void>() {
								@Override
								public void onSuccess(Void result) {
									Info.display("Сохранены", "Успешно сохранил");
									projectModulesView.update();
								}
							});
						}
					}
				});
				window.show();

			}
		});
		toolBar.add(selectCalculationDataButton);

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

		toolBar.add(new FieldLabel(getScaleComboBox(), "Масштаб"));
		toolBar.add(new FieldLabel(getFiltersComboBox(), "Скрыть"));

	}

	private ComboBox<KeyValueData> getScaleComboBox() {
		ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
		list.add(new KeyValueData(ScaleConstants.DAY, "День"));
		list.add(new KeyValueData(ScaleConstants.WEEK, "Неделя"));
		list.add(new KeyValueData(ScaleConstants.MONTH, "Месяц"));

		ComboBox<KeyValueData> scaleCombo = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		scaleCombo.setForceSelection(true);
		scaleCombo.setTypeAhead(true);
		scaleCombo.setTriggerAction(TriggerAction.ALL);
		scaleCombo.setEditable(false);
		scaleCombo.setValue(list.get(1));

		scaleCombo.addSelectionHandler(new SelectionHandler<KeyValueData>() {

			@Override
			public void onSelection(SelectionEvent<KeyValueData> event) {
				projectModulesView.getGantt().changeScale(event.getSelectedItem().getKey());
			}
		});

		return scaleCombo;
	}

	private ComboBox<KeyValueData> getFiltersComboBox() {
		ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
		list.add(new KeyValueData(null, ""));
		list.add(new KeyValueData(ResourceTypesConsts.ORDER, "Закупки"));
		list.add(new KeyValueData(ResourceTypesConsts.ASSEMBLAGE, "Сборку"));

		ComboBox<KeyValueData> filterCombo = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		filterCombo.setForceSelection(true);
		filterCombo.setTypeAhead(true);
		filterCombo.setTriggerAction(TriggerAction.ALL);
		filterCombo.setEditable(false);
		filterCombo.setValue(list.get(0));

		filterCombo.addSelectionHandler(new SelectionHandler<KeyValueData>() {

			@Override
			public void onSelection(SelectionEvent<KeyValueData> event) {
				projectModulesView.getGantt().setFilterResourceType(event.getSelectedItem().getKey());
			}
		});

		return filterCombo;
	}

}
