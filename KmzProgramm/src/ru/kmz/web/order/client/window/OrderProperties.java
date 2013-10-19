package ru.kmz.web.order.client.window;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.CommonDirectoryWindow;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class OrderProperties extends CommonDirectoryWindow<OrderProxy> {

	private TextField orderName;
	private TextField customerName;
	private TextField legalNumber;

	public OrderProperties() {
		super();
		setPixelSize(500, 150);
		setHeadingText("Заказ");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		orderName = new TextField();
		orderName.setAllowBlank(false);
		orderName.setEmptyText("Введите название...");
		p.add(new FieldLabel(orderName, "Название"), new VerticalLayoutData(1, -1));

		customerName = new TextField();
		customerName.setAllowBlank(false);
		customerName.setEmptyText("Введите название...");
		p.add(new FieldLabel(customerName, "Заказчик"), new VerticalLayoutData(1, -1));

		legalNumber = new TextField();
		legalNumber.setAllowBlank(false);
		legalNumber.setEmptyText("Введите номер...");
		p.add(new FieldLabel(legalNumber, "Договор"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(OrderProxy object) {
		this.object = object;
		orderName.setValue(object.getName());
		customerName.setValue(object.getCustomer());
		legalNumber.setValue(object.getLegalNumber());
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new OrderProxy();
		object.setName(orderName.getValue());
		object.setCustomer(customerName.getValue());
		object.setLegalNumber(legalNumber.getValue());
		OrderCommon.getService().editOrder(object, new AsyncCallbackWithErrorMessage<OrderProxy>() {
			@Override
			public void onSuccess(OrderProxy result) {
				Info.display("Данные сохранены", result.getName());
				if (updatableForm != null)
					updatableForm.update();
			}
		});
	}

}
