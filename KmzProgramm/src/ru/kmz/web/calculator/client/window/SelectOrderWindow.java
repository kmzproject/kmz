package ru.kmz.web.calculator.client.window;

import java.util.List;

import ru.kmz.web.calculator.client.CalculatorModuleView;
import ru.kmz.web.calculator.shared.OrderProxy;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.client.window.CommonSelectWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;

public class SelectOrderWindow extends CommonSelectWindow<KeyValueData> {

	private ComboBox<KeyValueData> orderBox;

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();

		CalculatorModuleView.getService().getOrders(new AsyncCallback<List<OrderProxy>>() {
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

				p.add(new FieldLabel(orderBox, "Шаблон"), new VerticalLayoutData(1, -1));

			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "load error " + caught);
			}
		});
		return p;
	}

	@Override
	protected KeyValueData getSelectedValue() {
		return orderBox.getValue();
	}
}
