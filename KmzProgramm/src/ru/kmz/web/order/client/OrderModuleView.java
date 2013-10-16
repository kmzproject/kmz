package ru.kmz.web.order.client;

import java.util.List;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.order.client.window.OrderProperties;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class OrderModuleView extends AbstarctModuleView<VerticalPanel> implements IUpdatable {

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
		container = new VerticalPanel();
		container.setSpacing(10);

		createButtons();

		grid = OrdersGrid.getCalculatorGrid();
		container.add(grid);
	}

	private void createButtons() {
		HorizontalPanel buttonContainer = new HorizontalPanel();
		buttonContainer.setSpacing(10);
		container.add(buttonContainer);

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
		editButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				List<OrderProxy> list = grid.getSelectionModel().getSelectedItems();
				if (list == null || list.size() != 1) {
					Info.display("Предупреждение", "Невозможно произвести редактирование");
					return;
				}
				OrderProxy object = list.get(0);
				OrderProperties window = new OrderProperties();
				window.setUpdatable(OrderModuleView.this);
				window.setData(object);
				window.show();
			}
		});
		buttonContainer.add(addButton);
		buttonContainer.add(editButton);
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
