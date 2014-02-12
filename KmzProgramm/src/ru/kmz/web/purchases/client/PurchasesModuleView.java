package ru.kmz.web.purchases.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGridRowSelectHandler;
import ru.kmz.web.common.client.menu.GridContextMenuItem;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class PurchasesModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static PurchasesModuleView instanse;
	private final static PurchasesModuleServiceAsync service = GWT.create(PurchasesModuleService.class);

	private PurchasesGrid grid;
	private DateField dateFrom, dateTo;

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
		container = new VerticalLayoutContainer();

		grid = PurchasesGrid.getCalculatorGrid();

		container.add(createToolBar());

		container.add(grid);
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton setStartedTextButton = new TextButton("Работа началась");
		setStartedTextButton.addSelectHandler(new CommonGridRowSelectHandler<PurchaseProxy>(grid) {
			@Override
			protected void onSelect(PurchaseProxy object) {
				setStarted(object);
			}
		});
		GridContextMenuItem<PurchaseProxy> setStartedMenuItem = new GridContextMenuItem<PurchaseProxy>(grid, "Работа началась") {

			@Override
			protected void onSelection(PurchaseProxy selectedObject) {
				setStarted(selectedObject);
			}
		};

		GridContextMenuItem<PurchaseProxy> setPlannedMenuItem = new GridContextMenuItem<PurchaseProxy>(grid, "Работа запланирована") {

			@Override
			protected void onSelection(PurchaseProxy selectedObject) {
				setPlanned(selectedObject);
			}
		};

		TextButton compliteTextButton = new TextButton("Отметить как выполненное");
		compliteTextButton.addSelectHandler(new CommonGridRowSelectHandler<PurchaseProxy>(grid) {
			@Override
			protected void onSelect(PurchaseProxy object) {
				setComplited(object);
			}
		});

		GridContextMenuItem<PurchaseProxy> compliteMenuItem = new GridContextMenuItem<PurchaseProxy>(grid, "Отметить как выполненное") {

			@Override
			protected void onSelection(PurchaseProxy selectedObject) {
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
		grid.getContextMenu().add(setPlannedMenuItem);
		grid.getContextMenu().add(compliteMenuItem);

		toolBar.add(setStartedTextButton);
		toolBar.add(compliteTextButton);
		toolBar.add(refreshButton);
		toolBar.add(new FieldLabel(dateFrom, "Начиная с"));
		toolBar.add(new FieldLabel(dateTo, "по"));

		return toolBar;
	}

	private void setComplited(PurchaseProxy proxy) {
		getService().complitePurchase(proxy.getId(), new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				update();
			}
		});
	}

	private void setStarted(PurchaseProxy proxy) {
		getService().setTaskAsStartedPersents(proxy.getId(), new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				update();
			}
		});
	}

	private void setPlanned(PurchaseProxy proxy) {
		getService().setTaskAsPlannedPersents(proxy.getId(), new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				update();
			}
		});
	}

	public static PurchasesModuleServiceAsync getService() {
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
