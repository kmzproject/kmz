package ru.kmz.web.order.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGridRowSelectHandler;
import ru.kmz.web.common.client.menu.GridContextMenuItem;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.main.client.KmzNavigation;
import ru.kmz.web.order.client.window.OrderProperties;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.shared.OrderProxy;
import ru.kmz.web.projects.client.ProjectsModuleView;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class OrderModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static OrderModuleView instanse;
	private OrdersGrid grid;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Заказы";
	}

	public static OrderModuleView getInstance() {
		if (instanse == null)
			instanse = new OrderModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		grid = OrdersGrid.getOrderGrid();
		createContextMenu();
		container.add(createToolBar());
		container.add(grid);
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton addButton = new TextButton("Добавить");
		addButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				OrderProperties window = new OrderProperties();
				window.setUpdatable(OrderModuleView.this);
				window.show();
			}
		});
		TextButton editButton = new TextButton("Редактировать");
		editButton.addSelectHandler(new CommonGridRowSelectHandler<OrderProxy>(grid) {
			@Override
			protected void onSelect(OrderProxy object) {
				editOrder(object);
			}
		});

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});

		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(refreshButton);

		return toolBar;
	}

	private void createContextMenu() {
		GridContextMenuItem<OrderProxy> editMenuItem = new GridContextMenuItem<OrderProxy>(grid, "Редактировать") {

			@Override
			protected void onSelection(OrderProxy selectedObject) {
				editOrder(selectedObject);
			}
		};

		GridContextMenuItem<OrderProxy> showGanttMenuItem = new GridContextMenuItem<OrderProxy>(grid, "Показать диаграмму") {

			@Override
			protected void onSelection(OrderProxy selectedObject) {
				ProjectsModuleView.getInstance().setOrder(selectedObject.getId());
				KmzNavigation.getInstance().showModule(ProjectsModuleView.getInstance());
			}
		};

		GridContextMenuItem<OrderProxy> deleteMenuItem = new GridContextMenuItem<OrderProxy>(grid, "Удалить") {

			@Override
			protected void onSelection(OrderProxy selectedObject) {
				deleteOrder(selectedObject);
			}
		};

		grid.getContextMenu().add(editMenuItem);
		grid.getContextMenu().add(showGanttMenuItem);
		grid.getContextMenu().add(deleteMenuItem);

	}

	private void editOrder(OrderProxy order) {
		OrderProperties window = new OrderProperties();
		window.setUpdatable(OrderModuleView.this);
		window.setData(order);
		window.show();
	}

	private void deleteOrder(OrderProxy order) {
		OrderCommon.getService().deleteOrder(order.getId(), new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				update();
			}
		});
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
