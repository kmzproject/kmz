package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.data.KeyStringValueDataProperties;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;
import ru.kmz.web.projects.client.window.GanttDataFilterWindow;
import ru.kmz.web.projects.client.window.SelectCalculationInfo;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProjectsToolBar implements IsWidget {

	private ToolBar toolBar;
	private ProjectsModuleView projectModulesView;
	private GanttDataFilterWindow filterWindow;

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

	public GanttDataFilter getFilter() {
		return filterWindow.getObject();
	}

	private void createButtonPanel() {
		filterWindow = new GanttDataFilterWindow();
		toolBar = new ToolBar();

		TextButton selectCalculationDataButton = new TextButton("Добавить новое изделиe");
		selectCalculationDataButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				SelectCalculationInfo window = new SelectCalculationInfo();
				window.setUpdatable(new IUpdatableWithValue<CalculatorInputDataProxy>() {

					@Override
					public void update(final CalculatorInputDataProxy inputData) {
						projectModulesView.createNewProduct(inputData);
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

		toolBar.add(new FieldLabel(getScaleComboBox(), "Масштаб"));

		TextButton filterButton = new TextButton("Фильтр");
		filterButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				filterWindow = new GanttDataFilterWindow();
				filterWindow.setUpdatable(new IUpdatable() {
					@Override
					public void update() {
						filterWindow.hide();
						GanttDataFilter filter = filterWindow.getObject();
						projectModulesView.update(filter);
					}
				});
				filterWindow.show();
			}
		});
		toolBar.add(filterButton);

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				projectModulesView.update();
			}
		});
		toolBar.add(refreshButton);

//		toolBar.add(new FieldLabel(getFiltersComboBox(), "Скрыть"));

	}

	private ComboBox<KeyValueData<String>> getScaleComboBox() {
		ListStore<KeyValueData<String>> list = new ListStore<KeyValueData<String>>(KeyStringValueDataProperties.prop.key());
		list.add(new KeyValueData<String>(ScaleConstants.DAY, "День"));
		list.add(new KeyValueData<String>(ScaleConstants.WEEK, "Неделя"));
		list.add(new KeyValueData<String>(ScaleConstants.MONTH, "Месяц"));

		ComboBox<KeyValueData<String>> scaleCombo = new ComboBox<KeyValueData<String>>(list, KeyStringValueDataProperties.prop.value());
		scaleCombo.setForceSelection(true);
		scaleCombo.setTypeAhead(true);
		scaleCombo.setTriggerAction(TriggerAction.ALL);
		scaleCombo.setEditable(false);
		scaleCombo.setValue(list.get(1));

		scaleCombo.addSelectionHandler(new SelectionHandler<KeyValueData<String>>() {

			@Override
			public void onSelection(SelectionEvent<KeyValueData<String>> event) {
				projectModulesView.getGantt().changeScale(event.getSelectedItem().getKey());
			}
		});

		return scaleCombo;
	}
}
