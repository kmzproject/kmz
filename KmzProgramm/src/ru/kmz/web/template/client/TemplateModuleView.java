package ru.kmz.web.template.client;

import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateModuleView implements EntryPoint, IsWidget, IKmzModule, IUpdatableWithValue<Integer> {

	private final static TemplateModuleServiceAsync templateMpduleService = GWT.create(TemplateModuleService.class);

	private static TemplateModuleView instanse;
	private Container container;
	private Container treeContainer;

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
		if (container == null) {
			createContainer();
		}
		return container;
	}

	private void createContainer() {
		container = new VerticalLayoutContainer();
		TextButton select = new TextButton("Выбрать");
		select.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TemplateSelectWindow window = new TemplateSelectWindow();
				window.setUpdatable(TemplateModuleView.this);
				window.show();
			}
		});
		container.add(select);
		treeContainer = new HorizontalLayoutContainer();
		container.add(treeContainer);
	}

	public static TemplateModuleServiceAsync getService() {
		return templateMpduleService;
	}

	public static TemplateModuleView getInstance() {
		if (instanse == null)
			instanse = new TemplateModuleView();
		return instanse;
	}

	@Override
	public void update(Integer value) {
		getService().getData(value, new AsyncCallback<TemplateTreeDataProxy>() {

			@Override
			public void onSuccess(TemplateTreeDataProxy result) {
				treeContainer.clear();
				TemplateTree tree = new TemplateTree();
				tree.setRoot(result.getTreeRoot());
				treeContainer.add(tree);
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "Load error");
			}
		});

	}

}
