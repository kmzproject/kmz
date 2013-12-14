package ru.kmz.web.common.client.control;

import ru.kmz.web.common.client.data.KeyStringValueDataProperties;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.shared.ResourceTypesConsts;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class ResourceComboBoxUtils {

	public static ComboBox<KeyValueData<String>> createResourceComboBox() {
		final ListStore<KeyValueData<String>> list = new ListStore<KeyValueData<String>>(KeyStringValueDataProperties.prop.key());

		list.add(new KeyValueData<String>(ResourceTypesConsts.PURCHASE));
		list.add(new KeyValueData<String>(ResourceTypesConsts.ASSEMBLAGE));
		list.add(new KeyValueData<String>(ResourceTypesConsts.PREPARE));

		ComboBox<KeyValueData<String>> resourceBox = new ComboBox<KeyValueData<String>>(list, KeyStringValueDataProperties.prop.value());
		resourceBox.setForceSelection(true);
		resourceBox.setTypeAhead(true);
		resourceBox.setTriggerAction(TriggerAction.ALL);
		resourceBox.setEditable(false);
		resourceBox.setValue(list.get(0));
		resourceBox.setWidth(220);

		return resourceBox;

	}

}
