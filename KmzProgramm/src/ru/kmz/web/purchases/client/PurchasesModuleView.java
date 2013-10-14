package ru.kmz.web.purchases.client;

import ru.kmz.web.common.client.AbstarctModuleView;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PurchasesModuleView extends AbstarctModuleView<VerticalPanel> {

	private static PurchasesModuleView instanse;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Закупки";
	}

	public static PurchasesModuleView getInstance() {
		if (instanse == null)
			instanse = new PurchasesModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalPanel();
		container.setSpacing(10);
	}

}
