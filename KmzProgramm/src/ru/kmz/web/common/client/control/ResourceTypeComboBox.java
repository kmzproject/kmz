package ru.kmz.web.common.client.control;

import ru.kmz.web.common.client.data.KeyStringValueDataProperties;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.shared.ResourceTypesConsts;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class ResourceTypeComboBox extends ComboBox<KeyValueData<String>> {

	private static ListStore<KeyValueData<String>> list;
	static {
		list = new ListStore<KeyValueData<String>>(KeyStringValueDataProperties.prop.key());
		list.add(new KeyValueData<String>(ResourceTypesConsts.ASSEMBLAGE));
		list.add(new KeyValueData<String>(ResourceTypesConsts.PREPARE));
		list.add(new KeyValueData<String>(ResourceTypesConsts.PURCHASE));
		list.add(new KeyValueData<String>(ResourceTypesConsts.FOLDER));
	}

	public void setValue(String value) {
		for (KeyValueData<String> data : list.getAll()) {
			if (data.getValue().equals(value)) {
				this.setValue(data);
				return;
			}
		}
	}

	public ResourceTypeComboBox() {
		super(list, KeyStringValueDataProperties.prop.value());
		setForceSelection(true);
		setTypeAhead(true);
		setTriggerAction(TriggerAction.ALL);
		setEditable(false);
		setValue(list.get(0));
	}

}
