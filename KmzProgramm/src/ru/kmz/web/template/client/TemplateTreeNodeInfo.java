package ru.kmz.web.template.client;

import ru.kmz.web.common.client.control.ResourceComboBoxUtils;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.shared.TemplateTreeNodeBaseProxy;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateTreeNodeInfo implements IsWidget {

	private FlowLayoutContainer container;

	private TextField name;
	private ComboBox<KeyValueData<String>> resourceType;
	private TextField duration;
	private SelectHandler selectHandler;

	public TemplateTreeNodeInfo(SelectHandler selectHandler) {
		this.selectHandler = selectHandler;
	}

	@Override
	public Widget asWidget() {
		if (container == null)
			createContainer();

		return container;
	}

	private void createContainer() {
		container = new FlowLayoutContainer();

		EnterKeyDownHandler handler = new EnterKeyDownHandler();

		name = new TextField();
		name.setWidth(200);
		name.addKeyDownHandler(handler);
		container.add(new FieldLabel(name, "Название"));

		resourceType = ResourceComboBoxUtils.createResourceComboBox();
		resourceType.setWidth(200);
		resourceType.addKeyDownHandler(handler);
		container.add(new FieldLabel(resourceType, "Тип ресурса"));

		duration = new TextField();
		duration.setWidth(200);
		duration.setValue("0");
		duration.addKeyDownHandler(handler);
		container.add(new FieldLabel(duration, "Время"));
	}

	public void setValue(TemplateTreeNodeBaseProxy value) {
		name.setValue(value.getName());
		duration.setValue(value.getDuration() + "");
		resourceType.setValue(new KeyValueData<String>(value.getResourceType()));
	}

	public TemplateTreeNodeBaseProxy fillValue(TemplateTreeNodeBaseProxy proxy) {
		proxy.setName(name.getText());
		try {
			proxy.setResourceType(resourceType.getValueOrThrow().getKey());
			proxy.setDuration(IntegerParser.instance().parse(duration.getText()));
		} catch (Exception ex) {
			Info.display("Error", "Не верно заполнено поле Время.");
			return null;
		}
		return proxy;
	}

	private class EnterKeyDownHandler implements KeyDownHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				if (selectHandler != null) {
					selectHandler.onSelect(null);
				}
			}
		}

	}
}
