package ru.kmz.web.projects.client;

import ru.kmz.web.common.client.AbstarctModuleView;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProjectsModuleView extends AbstarctModuleView {

	private static ProjectsModuleView instanse;
	private HorizontalPanel gantContainer;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Модуль производство";
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

		gantContainer = new HorizontalPanel();
		container.add(gantContainer);
	}

}
