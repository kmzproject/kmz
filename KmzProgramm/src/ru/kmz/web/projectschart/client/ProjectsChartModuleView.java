package ru.kmz.web.projectschart.client;

import java.util.List;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProjectsChartModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static ProjectsChartModuleView instanse;
	private static ProjectsChartServiceAsync service = GWT.create(ProjectsChartService.class);

	private FunctioningCapacityChart chart;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "График загрузки";
	}

	public static ProjectsChartModuleView getInstance() {
		if (instanse == null)
			instanse = new ProjectsChartModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		container.add(createToolBar());

		chart = new FunctioningCapacityChart();
		container.add(chart);

	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});
		toolBar.add(refreshButton);

		return toolBar;
	}
	
	public static ProjectsChartServiceAsync getService(){
		return service;
	}

	@Override
	public void update() {
		getService().getFunctioningCapacity(new AsyncCallbackWithErrorMessage<List<FunctioningCapacityProxy>>() {

			@Override
			public void onSuccess(List<FunctioningCapacityProxy> result) {
				chart.setData(result);
			}
			
		});
	}
}
