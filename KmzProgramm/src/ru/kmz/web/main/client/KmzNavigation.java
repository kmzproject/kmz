package ru.kmz.web.main.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.administration.client.AdministrationModuleView;
import ru.kmz.web.calendar.client.CalendarModuleView;
import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.order.client.OrderModuleView;
import ru.kmz.web.production.client.ProductionModuleView;
import ru.kmz.web.products.client.ProductsModuleView;
import ru.kmz.web.projects.client.ProjectsModuleView;
import ru.kmz.web.projectschart.client.ProjectsChartModuleView;
import ru.kmz.web.purchases.client.PurchasesModuleView;
import ru.kmz.web.template.client.TemplateModuleView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class KmzNavigation implements IsWidget {

	private KmzProgramm programm;

	public KmzNavigation(KmzProgramm programm) {
		this.programm = programm;
	}

	private List<IKmzModule> getMdules() {
		List<IKmzModule> list = new ArrayList<IKmzModule>();
		list.add(ProjectsModuleView.getInstance());
		list.add(ProjectsChartModuleView.getInstance());
		list.add(OrderModuleView.getInstance());
		list.add(ProductsModuleView.getInstance());
		list.add(ProductionModuleView.getInstance());
		list.add(PurchasesModuleView.getInstance());
		list.add(TemplateModuleView.getInstance());
		list.add(CalendarModuleView.getInstance());
		list.add(AdministrationModuleView.getInstance());
		return list;
	}

	@Override
	public Widget asWidget() {
		VBoxLayoutContainer c = new VBoxLayoutContainer();
		c.setPadding(new Padding(5));
		c.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);

		List<IKmzModule> list = getMdules();

		for (IKmzModule iKmzModule : list) {
			TextButton button = new TextButton(iKmzModule.getModuleName());
			button.setEnabled(iKmzModule.getEnabled());
			button.addSelectHandler(new NavigationSelectHandler(iKmzModule));
			c.add(button, new BoxLayoutData(new Margins(0, 0, 5, 0)));
		}

		Anchor logoutLink = new Anchor("Выход");
		logoutLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.Location.assign("j_spring_security_logout");
			}
		});

		c.add(logoutLink);

		return c;
	}

	private class NavigationSelectHandler implements SelectHandler {

		private IKmzModule module;

		private NavigationSelectHandler(IKmzModule module) {
			this.module = module;
		}

		@Override
		public void onSelect(SelectEvent event) {
			programm.addWidgetTab(module);
		}

	}
}
