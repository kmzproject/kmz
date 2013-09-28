package ru.kmz.web.resources.client;

import ru.kmz.web.common.client.IKmzModule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResourcesModuleView implements EntryPoint, IsWidget, IKmzModule {

	private static ResourcesModuleView instance;
	private VerticalPanel container;

	@Override
	public void onModuleLoad() {
		instance = this;
		if (RootPanel.get("resources") != null)
			RootPanel.get("resources").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Модуль ресурсов";
	}

	@Override
	public Widget asWidget() {
		if (container == null) {
			createContainer();
		}
		return container;
	}

	private void createContainer() {
		container = new VerticalPanel();
		container.setSpacing(10);
	}

	public static ResourcesModuleView getInstance() {
		if (instance == null)
			instance = new ResourcesModuleView();
		return instance;
	}

}
