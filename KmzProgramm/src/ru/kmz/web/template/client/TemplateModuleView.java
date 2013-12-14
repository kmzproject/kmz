package ru.kmz.web.template.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.ProgressOperationMessageBoxUtils;
import ru.kmz.web.template.client.window.TemplateNameWindow;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class TemplateModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private final static TemplateModuleServiceAsync service = GWT.create(TemplateModuleService.class);

	private static TemplateModuleView instanse;
	private Label label;
	private Container treeContainer;
	private TemplateTreeDataProxy selectedTemplate;
	private TextButton chancgeTemplate;
	private TemplatesGrid grid;

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

		grid = TemplatesGrid.getTemplateGrid();
		grid.addRowClickHandler(new RowClickHandler() {

			@Override
			public void onRowClick(RowClickEvent event) {
				int rowIndex = event.getRowIndex();
				TemplateTreeDataProxy t = (TemplateTreeDataProxy) event.getSource().getStore().get(rowIndex);
				updateTemplateView(t.getId());
			}
		});
		container.add(grid);

		container.add(label = new Label());

		treeContainer = new HorizontalLayoutContainer();
		container.add(treeContainer);
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		chancgeTemplate = new TextButton("Изменить");
		chancgeTemplate.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TemplateNameWindow window = new TemplateNameWindow();
				window.setData(selectedTemplate);
				window.setUpdatable(TemplateModuleView.this);
				window.show();
			}
		});
		chancgeTemplate.setEnabled(false);

		TextButton createTemplate = new TextButton("Добавить");
		createTemplate.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TemplateNameWindow window = new TemplateNameWindow();
				window.setUpdatable(TemplateModuleView.this);
				window.show();
			}
		});
		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});

		toolBar.add(createTemplate);
		toolBar.add(chancgeTemplate);
		toolBar.add(refreshButton);

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

	private void updateTemplateView(long tamplateId) {
		final AutoProgressMessageBox box = ProgressOperationMessageBoxUtils.getServerRequest();
		box.show();
		getService().getData(tamplateId, new AsyncCallbackWithErrorMessage<TemplateTreeDataProxy>(box) {

			@Override
			public void onSuccess(TemplateTreeDataProxy result) {
				selectedTemplate = result;
				chancgeTemplate.setEnabled(true);

				treeContainer.clear();
				TemplateTree tree = new TemplateTree();
				tree.setRoot(result.getTreeRoot());
				treeContainer.add(tree);

				label.setText(result.getName());
				box.hide();
			}
		});
	}

	@Override
	public void update() {
		grid.updateData();
	}
}
