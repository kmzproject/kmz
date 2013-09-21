package ru.kmz.web.main.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

public class KmzNavigation implements IsWidget {

	@Override
	public Widget asWidget() {
		TextButton buttonTree = new TextButton("Модуль расчетов");
		TextButton buttonCalculator = new TextButton("Модуль шаблонов");

		VBoxLayoutContainer c = new VBoxLayoutContainer();
		c.setPadding(new Padding(5));
		c.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);
		c.add(buttonTree, new BoxLayoutData(new Margins(0, 0, 5, 0)));
		c.add(buttonCalculator, new BoxLayoutData(new Margins(0, 0, 5, 0)));
		return c;
	}
}
