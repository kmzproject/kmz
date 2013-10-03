package ru.kmz.web.common.client.control;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.shared.ResourceTypesConsts;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class ResourceTypeComboBox extends ComboBox<KeyValueData> {

	private static ListStore<KeyValueData> list;

	static {
		list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
		list.add(new KeyValueData(ResourceTypesConsts.ASSEMBLAGE));
		list.add(new KeyValueData(ResourceTypesConsts.PREPARE));
		list.add(new KeyValueData(ResourceTypesConsts.ORDER));
		list.add(new KeyValueData(ResourceTypesConsts.FOLDER));
	}

	public void setValue(String value) {
		for (KeyValueData data : list.getAll()) {
			if (data.getKey().equals(value)) {
				this.setValue(data);
				return;
			}
		}
	}

	public ResourceTypeComboBox() {
		super(list, KeyValueDataProperties.prop.value());
		setForceSelection(true);
		setTypeAhead(true);
		setTriggerAction(TriggerAction.ALL);
		setEditable(false);
		setValue(list.get(0));
	}

}
