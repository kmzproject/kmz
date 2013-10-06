package ru.kmz.web.template.client;

import java.util.List;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.client.window.CommonSelectWindow;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateSelectWindow extends CommonSelectWindow<KeyValueData> {

	private ComboBox<KeyValueData> templateBox;

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();

		TemplateModuleView.getService().getTemplateList(new AsyncCallback<List<TemplateTreeDataProxy>>() {

			@Override
			public void onSuccess(List<TemplateTreeDataProxy> result) {
				ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
				for (TemplateTreeDataProxy proxy : result) {
					list.add(new KeyValueData(proxy.getId(), proxy.getName()));
				}
				templateBox = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
				templateBox.setForceSelection(true);
				templateBox.setTypeAhead(true);
				templateBox.setTriggerAction(TriggerAction.ALL);
				templateBox.setEditable(false);
				templateBox.setValue(list.get(0));
				templateBox.setWidth(300);

				p.add(new FieldLabel(templateBox, "Шаблон"), new VerticalLayoutData(1, -1));
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
		return templateBox.getValue();
	}
}
