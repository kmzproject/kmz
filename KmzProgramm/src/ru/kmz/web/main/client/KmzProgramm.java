package ru.kmz.web.main.client;

import ru.kmz.web.common.client.IKmzModule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class KmzProgramm implements IsWidget, EntryPoint {

	private TabPanel center;

	public void addWidgetTab(IKmzModule module) {
		center.add(module, new TabItemConfig(module.getModuleName(), true));
		center.setActiveWidget(center.getWidget(center.getWidgetCount() - 1));
	}

	public Widget asWidget() {

		final BorderLayoutContainer con = new BorderLayoutContainer();
		con.setBorders(true);

		ContentPanel west = new ContentPanel();
		west.add(new KmzNavigation(this));
		center = new TabPanel();
		center.setAnimScroll(true);
		center.setTabScroll(true);
		center.setCloseContextMenu(true);

		KmzHello hello = new KmzHello();
		center.add(hello, new TabItemConfig(hello.getModuleName(), true));

		BorderLayoutData westData = new BorderLayoutData(150);
		westData.setCollapsible(true);
		westData.setSplit(true);
		westData.setCollapseMini(true);
		westData.setMargins(new Margins(0, 5, 0, 5));

		MarginData centerData = new MarginData();

		con.setWestWidget(west, westData);
		con.setCenterWidget(center, centerData);

		SimpleContainer simple = new SimpleContainer();
		simple.add(con, new MarginData(10));

		return simple;
	}

	public void onModuleLoad() {
		Widget con = asWidget();
		Viewport viewport = new Viewport();
		viewport.add(con);
		RootPanel.get().add(viewport);
	}

}
