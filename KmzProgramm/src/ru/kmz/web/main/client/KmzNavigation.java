package ru.kmz.web.main.client;

import ru.kmz.web.calculator.client.CalculatorModuleView;
import ru.kmz.web.common.client.IKmzModule;
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

	@Override
	public Widget asWidget() {
		CalculatorModuleView calculator = CalculatorModuleView.getInstance();
		TemplateModuleView template = TemplateModuleView.getInstance();
		ResourcesModuleView resources = ResourcesModuleView.getInstance();

		TextButton buttonCalculator = new TextButton(calculator.getModuleName());
		TextButton buttonTree = new TextButton(template.getModuleName());
		TextButton buttonResources = new TextButton(resources.getModuleName());

		buttonCalculator.addSelectHandler(new NavigationSelectHandler(calculator));
		buttonTree.addSelectHandler(new NavigationSelectHandler(template));
		buttonResources.addSelectHandler(new NavigationSelectHandler(resources));

		VBoxLayoutContainer c = new VBoxLayoutContainer();
		c.setPadding(new Padding(5));
		c.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);
		c.add(buttonCalculator, new BoxLayoutData(new Margins(0, 0, 5, 0)));
		c.add(buttonTree, new BoxLayoutData(new Margins(0, 0, 5, 0)));
		c.add(buttonResources, new BoxLayoutData(new Margins(0, 0, 5, 0)));

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
