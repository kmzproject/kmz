package ru.kmz.web.projectschart.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProjectsChartModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static ProjectsChartModuleView instanse;

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

	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		TextButton compliteTextButton = new TextButton("Отметить как выполненное");
		compliteTextButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
			}
		});
		toolBar.add(compliteTextButton);

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

	@Override
	public void update() {
	}

}
