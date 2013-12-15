package ru.kmz.web.production.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGridRowSelectHandler;
import ru.kmz.web.common.client.menu.GridContextMenuItem;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.ProductionProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProductionModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static ProductionModuleView instanse;
	private final static ProductionModuleServiceAsync service = GWT.create(ProductionModuleService.class);

	private ProductionGrid grid;
	private DateField dateFrom, dateTo;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Производство";
	}

	public static ProductionModuleView getInstance() {
		if (instanse == null)
			instanse = new ProductionModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		grid = ProductionGrid.getCalculatorGrid();
		container.add(createToolBar());

		container.add(grid);

	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton setStartedTextButton = new TextButton("Работа началась");
		setStartedTextButton.addSelectHandler(new CommonGridRowSelectHandler<ProductionProxy>(grid) {
			@Override
			protected void onSelect(ProductionProxy object) {
				setStarted(object);
			}
		});
		GridContextMenuItem<ProductionProxy> setStartedMenuItem = new GridContextMenuItem<ProductionProxy>(grid, "Работа началась") {

			@Override
			protected void onSelection(ProductionProxy selectedObject) {
				setStarted(selectedObject);
			}
		};

		TextButton compliteTextButton = new TextButton("Отметить как выполненное");
		compliteTextButton.addSelectHandler(new CommonGridRowSelectHandler<ProductionProxy>(grid) {
			@Override
			protected void onSelect(ProductionProxy object) {
				setComplited(object);
			}
		});
		GridContextMenuItem<ProductionProxy> compliteMenuItem = new GridContextMenuItem<ProductionProxy>(grid, "Отметить как выполненное") {

			@Override
			protected void onSelection(ProductionProxy selectedObject) {
				setComplited(selectedObject);
			}
		};

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});

		dateFrom = new DateField();
		dateTo = new DateField();

		grid.getContextMenu().add(setStartedMenuItem);
		grid.getContextMenu().add(compliteMenuItem);

		toolBar.add(setStartedTextButton);
		toolBar.add(compliteTextButton);
		toolBar.add(refreshButton);
		toolBar.add(new FieldLabel(dateFrom, "Начиная с"));
		toolBar.add(new FieldLabel(dateTo, "по"));

		return toolBar;
	}

	private void setComplited(ProductionProxy proxy) {
		getService().compliteProduction(proxy.getId(), new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				update();
			}
		});
	}

	private void setStarted(ProductionProxy proxy) {
		getService().setTaskAsStartedPersents(proxy.getId(), new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				update();
			}
		});
	}

	public static ProductionModuleServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		ProductElementTaskGridFilter filter = new ProductElementTaskGridFilter();
		filter.setFrom(dateFrom.getValue());
		filter.setTo(dateTo.getValue());
		grid.setFilter(filter);
		grid.updateData();
	}

}
