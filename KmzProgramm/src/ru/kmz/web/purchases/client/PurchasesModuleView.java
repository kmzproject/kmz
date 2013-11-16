package ru.kmz.web.purchases.client;

import java.util.List;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class PurchasesModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

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
		container = new VerticalLayoutContainer();

		container.add(createToolBar());

		grid = PurchasesGrid.getCalculatorGrid();
		container.add(grid);

	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		TextButton compliteTextButton = new TextButton("Отметить как выполненное");
		compliteTextButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				List<PurchaseProxy> list = grid.getSelectionModel().getSelectedItems();
				if (list == null || list.size() != 1) {
					Info.display("Предупреждение", "Невозможно произвести редактирование");
					return;
				}
				getService().complitePurchase(list.get(0).getId(), new AsyncCallbackWithErrorMessage<Void>() {
					@Override
					public void onSuccess(Void result) {
						update();
					}
				});
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

	public static PurchasesModuleServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
