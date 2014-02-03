package ru.kmz.web.ordercommon.client.control;

import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyLongValueDataProperties;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class OrderComboBoxUtils {

	public static ComboBox<KeyValueData<Long>> createOrderCompoBox() {
		final ListStore<KeyValueData<Long>> list = new ListStore<KeyValueData<Long>>(KeyLongValueDataProperties.prop.key());
		final ComboBox<KeyValueData<Long>> orderBox = new ComboBox<KeyValueData<Long>>(list, KeyLongValueDataProperties.prop.value());
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
					list.add(new KeyValueData<Long>(proxy.getId(), proxy.getNameAndCode()));
				}
			}

		});

		return orderBox;
	}
}
