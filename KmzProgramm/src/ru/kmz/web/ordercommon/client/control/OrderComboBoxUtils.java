package ru.kmz.web.ordercommon.client.control;

import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class OrderComboBoxUtils {

	public static ComboBox<KeyValueData> createOrderCompoBox(boolean addEmpty) {
		final ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
		if (addEmpty) {
			list.add(new KeyValueData("", null));
		}
		final ComboBox<KeyValueData> orderBox = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		orderBox.setForceSelection(true);
		orderBox.setTypeAhead(true);
		orderBox.setTriggerAction(TriggerAction.ALL);
		orderBox.setEditable(false);
		orderBox.setValue(list.get(0));
		orderBox.setWidth(220);

		OrderCommon.getService().getOrders(new AsyncCallbackWithErrorMessage<List<OrderProxy>>() {
			@Override
			public void onSuccess(List<OrderProxy> result) {
				for (OrderProxy proxy : result) {
					list.add(new KeyValueData(proxy.getId(), proxy.getName()));
				}
			}

		});

		return orderBox;
	}

	public static ComboBox<KeyValueData> createOrderCompoBox() {
		return createOrderCompoBox(false);
	}
}
