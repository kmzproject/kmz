package ru.kmz.web.template.client;

import java.text.ParseException;

import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateTreeNodeInfo implements IsWidget {

	private FlowLayoutContainer container;

	private TextField name;
	private TextField resourceType;
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

		name = new TextField();
		name.setWidth(200);
		container.add(new FieldLabel(name, "Название"));

		resourceType = new TextField();
		resourceType.setWidth(200);
		container.add(new FieldLabel(resourceType, "Тип ресурса"));

		duration = new TextField();
		duration.setWidth(200);
		container.add(new FieldLabel(duration, "Время"));

		resource = new TextField();
		resource.setWidth(200);
		container.add(new FieldLabel(resource, "Ресурс"));
	}

	public void setValue(TemplateTreeNodeBaseProxy value) {
		name.setValue(value.getName());
		duration.setValue(value.getDuration() + "");
		resourceType.setValue(value.getResourceType());
	}

	public void saveValue(TemplateTreeNodeBaseProxy proxy) {
		proxy.setName(name.getValue());
		try {
			proxy.setDuration(IntegerParser.instance().parse(duration.getValue()));
		} catch (ParseException ex) {
			Info.display("Error", "error " + ex);
		}
		proxy.setResourceType(resourceType.getValue());
	}
}
