package ru.kmz.web.ordercommon.client.window;

import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.client.window.CommonSelectWindow;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class OrderSelectWindow extends CommonSelectWindow<KeyValueData> {

	private ComboBox<KeyValueData> orderBox;

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();
		OrderCommon.getService().getOrders(new AsyncCallbackWithErrorMessage<List<OrderProxy>>() {
			@Override
			public void onSuccess(List<OrderProxy> result) {
				ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
				for (OrderProxy proxy : result) {
					list.add(new KeyValueData(proxy.getId(), proxy.getName()));
				}
				orderBox = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
				orderBox.setForceSelection(true);
				orderBox.setTypeAhead(true);
				orderBox.setTriggerAction(TriggerAction.ALL);
				orderBox.setEditable(false);
				orderBox.setValue(list.get(0));
				orderBox.setWidth(300);

				p.add(new FieldLabel(orderBox, "Заказ"), new VerticalLayoutData(1, -1));

			}
		});
		return p;
	}

	@Override
	protected KeyValueData getSelectedValue() {
		return orderBox.getValue();
	}
}
