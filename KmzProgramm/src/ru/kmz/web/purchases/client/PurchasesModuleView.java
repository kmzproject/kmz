package ru.kmz.web.purchases.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PurchasesModuleView extends AbstarctModuleView<VerticalPanel> implements IUpdatable {

	private static PurchasesModuleView instanse;
	private final static PurchasesModuleServiceAsync service = GWT.create(PurchasesModuleService.class);

	private PurchasesGrid grid;

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

		grid = PurchasesGrid.getCalculatorGrid();
		container.add(grid);

	}

	public static PurchasesModuleServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
