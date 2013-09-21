package ru.kmz.web.main.client;


import ru.kmz.web.common.client.IKmzModule;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class KmzHello implements IsWidget, IKmzModule {

	@Override
	public Widget asWidget() {
		TextArea a = new TextArea();
		a.setText("test1");
		return a;
	}

	@Override
	public String getModuleName() {
		return "Информация о системе";
	}
	
}
