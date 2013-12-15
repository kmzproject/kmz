package ru.kmz.web.products.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProductsModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static ProductsModuleView instanse;
	private final static ProductsModuleServiceAsync service = GWT.create(ProductsModuleService.class);

	private ProductsGrid grid;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Изделия";
	}

	public static ProductsModuleView getInstance() {
		if (instanse == null)
			instanse = new ProductsModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		grid = ProductsGrid.getCalculatorGrid();
		container.add(createToolBar());

		container.add(grid);

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

	public static ProductsModuleServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
