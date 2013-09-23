package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TemplateTreeNodeInfo implements IsWidget {

	private FlowLayoutContainer container;

	private TextField name;
	private TextField description;
	private TextField duration;
	private TextField resource;

	@Override
	public Widget asWidget() {
		if (container == null)
			createContainer();

		return container;
	}

	private void createContainer() {
		container = new FlowLayoutContainer();

		// Create the fields
		name = new TextField();
		name.setWidth(200);
		name.setEnabled(false);
		container.add(new FieldLabel(name, "Название"));

		description = new TextField();
		description.setWidth(200);
		description.setEnabled(false);
		container.add(new FieldLabel(description, "Описание"));

		duration = new TextField();
		duration.setWidth(200);
		duration.setEnabled(false);
		container.add(new FieldLabel(duration, "Время"));

		resource = new TextField();
		resource.setWidth(200);
		resource.setEnabled(false);
		container.add(new FieldLabel(resource, "Ресурс"));
	}

	public void setValue(TemplateTreeNodeBaseProxy value) {
		name.setValue(value.getName());
		duration.setValue(value.getDuration() + "");
	}
}
