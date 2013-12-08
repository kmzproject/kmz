package ru.kmz.web.administration.client.window;

import ru.kmz.web.common.client.window.CommonSelectWindow;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class SelectNewPasswordWindow extends CommonSelectWindow<String> {

	private TextField newPassword;

	public SelectNewPasswordWindow() {
		super();
		setPixelSize(370, 100);
	}

	@Override
	protected Container getInfoContainer() {
		FlowLayoutContainer container = new FlowLayoutContainer();

		newPassword = new TextField();
		container.add(new FieldLabel(newPassword, "Пароль"));

		return container;
	}

	@Override
	protected String getSelectedValue() {
		return newPassword.getValue();

	}
}
