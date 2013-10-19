package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ordercommon.client.window.OrderSelectWindow;
import ru.kmz.web.projects.client.window.SelectCalculationInfo;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class CalculateSaveButtons implements IsWidget {

	private ToolBar toolBar;
	private CalculatorInputDataProxy inputData;
	private IUpdatableWithValue<GanttData> listener;

	public CalculateSaveButtons(IUpdatableWithValue<GanttData> listener) {
		this.listener = listener;
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

						ProjectsModuleView.getService().getGantResultData(value, new AsyncCallback<GanttData>() {
							@Override
							public void onSuccess(GanttData result) {
								if (result.getError() != null) {
									Info.display("Error", "Ошибка при обработке " + result.getError());
								} else {
									listener.update(result);
								}
								CalculateSaveButtons.this.inputData = value;
								box.hide();
							}

							@Override
							public void onFailure(Throwable caught) {
								Info.display("Error", "This is error " + caught);
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
						ProjectsModuleView.getService().save(CalculateSaveButtons.this.inputData, value.getKey(), new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								Info.display("Сохранены", "Успешно сохранил");
								listener.update(null);
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
		toolBar.add(selectDataButton);

	}
}
