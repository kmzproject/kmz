package ru.kmz.web.templatecommon.client.window;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.CommonSelectWindow;
import ru.kmz.web.templatecommon.client.control.TemplateComboBoxUtils;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class TemplateSelectWindow extends CommonSelectWindow<KeyValueData> {

	private ComboBox<KeyValueData> templateBox;

	public TemplateSelectWindow() {
		super();
		setPixelSize(500, 50);
	}

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();

		templateBox = TemplateComboBoxUtils.createTemplateComboBox();

		p.add(new FieldLabel(templateBox, "Шаблон"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	protected KeyValueData getSelectedValue() {
		return templateBox.getValue();
	}
}
