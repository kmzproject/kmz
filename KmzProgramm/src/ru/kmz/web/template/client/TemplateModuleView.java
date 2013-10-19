package ru.kmz.web.template.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.templatecommon.client.window.TemplateSelectWindow;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class TemplateModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatableWithValue<KeyValueData> {

	private final static TemplateModuleServiceAsync service = GWT.create(TemplateModuleService.class);

	private static TemplateModuleView instanse;
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
		return "Шаблоны";
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		container.add(createToolBar());

		container.add(label = new Label());

		treeContainer = new HorizontalLayoutContainer();
		container.add(treeContainer);
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton select = new TextButton("Выбрать");
		select.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TemplateSelectWindow window = new TemplateSelectWindow();
				window.setUpdatable(TemplateModuleView.this);
				window.show();
			}
		});
		toolBar.add(select);
		return toolBar;
	}

	public static TemplateModuleServiceAsync getService() {
		return service;
	}

	public static TemplateModuleView getInstance() {
		if (instanse == null)
			instanse = new TemplateModuleView();
		return instanse;
	}

	@Override
	public void update(KeyValueData data) {
		getService().getData(data.getKey(), new AsyncCallbackWithErrorMessage<TemplateTreeDataProxy>() {

			@Override
			public void onSuccess(TemplateTreeDataProxy result) {
				treeContainer.clear();
				TemplateTree tree = new TemplateTree();
				tree.setRoot(result.getTreeRoot());
				treeContainer.add(tree);

				label.setText(result.getName());
			}
		});
	}
}
