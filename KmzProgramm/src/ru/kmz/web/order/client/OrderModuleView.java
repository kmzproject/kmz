package ru.kmz.web.order.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ordercommon.client.window.OrderSelectWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class OrderModuleView extends AbstarctModuleView implements IUpdatableWithValue<KeyValueData> {

	private static OrderModuleView instanse;
	private Label label;
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

		createButtons();

		gantContainer = new HorizontalPanel();
		container.add(gantContainer);
	}

	private void createButtons() {
		HorizontalPanel buttonContainer = new HorizontalPanel();
		buttonContainer.setSpacing(10);
		container.add(buttonContainer);

		label = new Label();
		buttonContainer.add(label);

		TextButton select = new TextButton("Выбрать");

		select.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				OrderSelectWindow window = new OrderSelectWindow();
				window.setUpdatable(OrderModuleView.this);
				window.show();
			}
		});
		buttonContainer.add(select);
	}

	@Override
	public void update(KeyValueData value) {
		label.setText(value.getValue());
	}

}
