package ru.kmz.web.template.client;

import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateModuleView implements EntryPoint, IsWidget, IKmzModule {

	private final static TemplateModuleServiceAsync templateMpduleService = GWT.create(TemplateModuleService.class);

	private static TemplateModuleView instanse;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("tree") != null) {
			RootPanel.get("tree").add(asWidget());
		}
	}

	@Override
	public String getModuleName() {
		return "Модуль шаблонов";
	}

	@Override
	public Widget asWidget() {
		final Container container = new HorizontalLayoutContainer();
		templateMpduleService.getData(null, new AsyncCallback<TemplateTreeDataProxy>() {

			@Override
			public void onSuccess(TemplateTreeDataProxy result) {
				TemplateTree tree = new TemplateTree();
				tree.setRoot(result.getTreeRoot());
				container.add(tree);
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "Load error");
			}
		});
		return container;
	}

	public static TemplateModuleServiceAsync getService() {
		return templateMpduleService;
	}

	public static TemplateModuleView getInstance() {
		if (instanse == null)
			instanse = new TemplateModuleView();
		return instanse;
	}

}
