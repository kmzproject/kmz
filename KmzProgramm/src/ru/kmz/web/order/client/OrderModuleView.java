package ru.kmz.web.order.client;

import ru.kmz.web.common.client.AbstarctModuleView;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widget.client.TextButton;

public class OrderModuleView extends AbstarctModuleView {

	private static OrderModuleView instanse;
	private HorizontalPanel gantContainer;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Модуль заказов";
	}

	public static OrderModuleView getInstance() {
		if (instanse == null)
			instanse = new OrderModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalPanel();
		container.setSpacing(10);

		gantContainer = new HorizontalPanel();

		gantContainer.add(new TextButton("Test"));

		container.add(gantContainer);
	}

}
