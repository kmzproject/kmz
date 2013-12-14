package ru.kmz.web.calendar.client;

import ru.kmz.web.calendar.client.window.CalculateCalendarWindow;
import ru.kmz.web.calendar.client.window.CalendarRecordProperties;
import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGridRowSelectHandler;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class CalendarModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatableWithValue<CalculateCalendarParamProxy>, IUpdatable {

	private final static CalendarModuleServiceAsync service = GWT.create(CalendarModuleService.class);

	private CalendarRecordsGrid grid;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null) {
			RootPanel.get("grid").add(asWidget());
		}
	}

	@Override
	public String getModuleName() {
		return "Календарь";
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		container.add(createToolBar());

		grid = CalendarRecordsGrid.getCalculatorGrid();
		container.add(grid);
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton calculateButton = new TextButton("Расчитать");
		calculateButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				CalculateCalendarWindow window = new CalculateCalendarWindow();
				window.setUpdatable(CalendarModuleView.this);
				window.show();
			}
		});
		toolBar.add(calculateButton);

		TextButton addButton = new TextButton("Добавить");
		addButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {

				CalendarRecordProperties window = new CalendarRecordProperties();
				window.setUpdatable(CalendarModuleView.this);
				window.show();
			}
		});
		toolBar.add(addButton);

		TextButton deleteButton = new TextButton("Удалить");
		deleteButton.addSelectHandler(new CommonGridRowSelectHandler<CalendarRecordProxy>(grid) {
			@Override
			protected void onSelect(CalendarRecordProxy selectedObject) {
				getService().deleteCalendarRecord(selectedObject.getId(), new AsyncCallbackWithErrorMessage<Void>() {
					@Override
					public void onSuccess(Void result) {
						Info.display("Успех", "Запись успешно удалена");
						grid.updateData();
					}
				});
			}
		});
		toolBar.add(deleteButton);

		TextButton updateButton = new TextButton("Обновить");
		updateButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});
		toolBar.add(updateButton);

		return toolBar;
	}

	public static CalendarModuleServiceAsync getService() {
		return service;
	}

	private static CalendarModuleView instanse;

	public static CalendarModuleView getInstance() {
		if (instanse == null)
			instanse = new CalendarModuleView();
		return instanse;
	}

	@Override
	public void update(CalculateCalendarParamProxy value) {
		getService().calculateWeekends(value, new AsyncCallbackWithErrorMessage<Void>() {

			@Override
			public void onSuccess(Void result) {
				Info.display("Изменения сохранены", "Выходные дни расчитаны и внесены в календарь.");
				grid.updateData();
			}

		});
	}

	@Override
	public void update() {
		grid.updateData();
	}
}
