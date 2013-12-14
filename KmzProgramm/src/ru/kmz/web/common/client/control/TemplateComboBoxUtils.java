package ru.kmz.web.common.client.control;

import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonModule;
import ru.kmz.web.common.client.data.KeyLongValueDataProperties;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.shared.TemplateTreeDataProxy;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class TemplateComboBoxUtils {

	public static ComboBox<KeyValueData<Long>> createTemplateComboBox() {
		final ListStore<KeyValueData<Long>> list = new ListStore<KeyValueData<Long>>(KeyLongValueDataProperties.prop.key());
		ComboBox<KeyValueData<Long>> templateBox = new ComboBox<KeyValueData<Long>>(list, KeyLongValueDataProperties.prop.value());
		templateBox.setForceSelection(true);
		templateBox.setTypeAhead(true);
		templateBox.setTriggerAction(TriggerAction.ALL);
		templateBox.setEditable(false);
		templateBox.setValue(list.get(0));
		templateBox.setWidth(220);

		CommonModule.getTemplateService().getTemplateList(new AsyncCallbackWithErrorMessage<List<TemplateTreeDataProxy>>() {
			@Override
			public void onSuccess(List<TemplateTreeDataProxy> result) {
				for (TemplateTreeDataProxy proxy : result) {
					list.add(new KeyValueData<Long>(proxy.getId(), proxy.getName()));
				}
			}
		});

		return templateBox;

	}
}
