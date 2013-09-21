package ru.kmz.web.calculator.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;

public class CalculatorResult implements IsWidget {

	@Override
	public Widget asWidget() {
		HorizontalPanel container = new HorizontalPanel();
		container.add(new TextButton("tes"));
		return container;
	}

}
