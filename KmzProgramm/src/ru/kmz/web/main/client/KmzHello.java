package ru.kmz.web.main.client;

import ru.kmz.web.common.client.IKmzModule;

import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class KmzHello implements IsWidget, IKmzModule {

	@Override
	public Widget asWidget() {
		Frame frame = new Frame("/HelloPage.html");
		return frame;
	}

	@Override
	public String getModuleName() {
		return "Информация о системе";
	}

	@Override
	public boolean getEnabled() {
		return true;
	}
}
