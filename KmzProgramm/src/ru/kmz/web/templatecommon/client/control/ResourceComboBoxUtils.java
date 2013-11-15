package ru.kmz.web.templatecommon.client.control;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.shared.ResourceTypesConsts;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class ResourceComboBoxUtils {

	public static ComboBox<KeyValueData> createResourceComboBox() {
		final ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());

		list.add(new KeyValueData(ResourceTypesConsts.PURCHASE));
		list.add(new KeyValueData(ResourceTypesConsts.ASSEMBLAGE));
		list.add(new KeyValueData(ResourceTypesConsts.PREPARE));

		ComboBox<KeyValueData> resourceBox = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		resourceBox.setForceSelection(true);
		resourceBox.setTypeAhead(true);
		resourceBox.setTriggerAction(TriggerAction.ALL);
		resourceBox.setEditable(false);
		resourceBox.setValue(list.get(0));
		resourceBox.setWidth(220);

		return resourceBox;

	}

}
