package ru.kmz.web.main.client;

import java.util.HashMap;
import java.util.Map;

import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.common.client.control.HistoryGrid;

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

	private Map<String, Integer> tabMap = new HashMap<String, Integer>();// Если
																			// будут
																			// закрывать
																			// закладки
																			// надо
																			// предусмотреть

	public void addWidgetTab(IKmzModule module) {
		String moduleName = module.getModuleName();
		if (!tabMap.containsKey(moduleName)) {
			center.add(module, new TabItemConfig(module.getModuleName(), false));
			tabMap.put(moduleName, center.getWidgetCount() - 1);
		}
		center.setActiveWidget(center.getWidget(tabMap.get(moduleName)));
	}

	public Widget asWidget() {

		final BorderLayoutContainer con = new BorderLayoutContainer();
		con.setBorders(true);

		ContentPanel west = new ContentPanel();
		ContentPanel south = new ContentPanel();
		south.add(HistoryGrid.getHistoryGrid());
		west.add(new KmzNavigation(this));
		center = new TabPanel();
		center.setAnimScroll(true);
		center.setTabScroll(true);
		center.setCloseContextMenu(true);

		KmzHello hello = new KmzHello();
		center.add(hello, new TabItemConfig(hello.getModuleName(), false));

		BorderLayoutData westData = new BorderLayoutData(150);
		westData.setCollapsible(true);
		westData.setSplit(true);
		westData.setCollapseMini(true);
		westData.setMargins(new Margins(0, 5, 0, 5));

		BorderLayoutData southData = new BorderLayoutData(100);
		southData.setMargins(new Margins(5));
		southData.setCollapsible(true);
		southData.setCollapseMini(true);

		MarginData centerData = new MarginData();

		con.setWestWidget(west, westData);
		con.setCenterWidget(center, centerData);
		con.setSouthWidget(south, southData);

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
