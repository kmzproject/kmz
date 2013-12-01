package ru.kmz.web.ordercommon.client.window;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.CommonSelectWindow;
import ru.kmz.web.ordercommon.client.control.OrderComboBoxUtils;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class OrderSelectWindow extends CommonSelectWindow<KeyValueData<Long>> {

	private ComboBox<KeyValueData<Long>> orderBox;

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();
		orderBox = OrderComboBoxUtils.createOrderCompoBox();
		p.add(new FieldLabel(orderBox, "Заказ"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	protected KeyValueData<Long> getSelectedValue() {
		return orderBox.getValue();
	}
}
