package ru.kmz.web.template.client;

import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.templatecommon.client.TemplateSelectWindow;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateModuleView implements EntryPoint, IsWidget, IKmzModule, IUpdatableWithValue<KeyValueData> {

	private final static TemplateModuleServiceAsync templateMpduleService = GWT.create(TemplateModuleService.class);

	private static TemplateModuleView instanse;
	private Container container;
	private Label label;
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
		createButtons();
		treeContainer = new HorizontalLayoutContainer();
		container.add(treeContainer);
	}

	private void createButtons() {
		HorizontalPanel buttonContainer = new HorizontalPanel();
		buttonContainer.setSpacing(10);
		container.add(buttonContainer);

		label = new Label();
		buttonContainer.add(label);

		TextButton select = new TextButton("Выбрать");
		select.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TemplateSelectWindow window = new TemplateSelectWindow();
				window.setUpdatable(TemplateModuleView.this);
				window.show();
			}
		});
		buttonContainer.add(select);
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
	public void update(KeyValueData data) {
		getService().getData(data.getKey(), new AsyncCallback<TemplateTreeDataProxy>() {

			@Override
			public void onSuccess(TemplateTreeDataProxy result) {
				treeContainer.clear();
				TemplateTree tree = new TemplateTree();
				tree.setRoot(result.getTreeRoot());
				treeContainer.add(tree);

				label.setText(result.getName());
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "Load error");
			}
		});
	}
}
