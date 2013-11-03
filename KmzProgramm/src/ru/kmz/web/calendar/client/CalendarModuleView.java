package ru.kmz.web.calendar.client;

import ru.kmz.web.common.client.AbstarctModuleView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class CalendarModuleView extends AbstarctModuleView<VerticalLayoutContainer> {

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
}
