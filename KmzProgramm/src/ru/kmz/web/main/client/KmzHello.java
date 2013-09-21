package ru.kmz.web.main.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class KmzHello implements IsWidget {

	@Override
	public Widget asWidget() {
		TextArea a = new TextArea();
		a.setText("test1");
		return a;
	}

	public TabItemConfig getTabItemConfig(){
		return new TabItemConfig("Информация о системе", false);
	}
}
