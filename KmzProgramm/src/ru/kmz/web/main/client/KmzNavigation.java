package ru.kmz.web.main.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.administration.client.AdministrationModuleView;
import ru.kmz.web.calculator.client.CalculatorModuleView;
import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.order.client.OrderModuleView;
import ru.kmz.web.projects.client.ProjectsModuleView;
import ru.kmz.web.purchases.client.PurchasesModuleView;
import ru.kmz.web.resources.client.ResourcesModuleView;
import ru.kmz.web.template.client.TemplateModuleView;

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
		list.add(OrderModuleView.getInstance());
		list.add(PurchasesModuleView.getInstance());
		list.add(TemplateModuleView.getInstance());
		list.add(ResourcesModuleView.getInstance());
		list.add(CalculatorModuleView.getInstance());
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
