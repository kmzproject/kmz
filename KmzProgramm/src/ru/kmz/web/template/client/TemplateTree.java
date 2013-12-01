package ru.kmz.web.template.client;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.TreeIconProvider;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeFolderProxy;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class TemplateTree implements IsWidget {

	private TemplateTreeNodeFolderProxy root;

	public void setRoot(TemplateTreeNodeFolderProxy root) {
		this.root = root;
	}

	private HorizontalPanel container;
	private TemplateTreeNodeInfo infoContainer;
	private Tree<TemplateTreeNodeBaseProxy, String> tree;

	@Override
	public Widget asWidget() {
		if (container != null)
			return container;
		container = new HorizontalPanel();
		container.setSpacing(10);

		TreeStore<TemplateTreeNodeBaseProxy> store = new TreeStore<TemplateTreeNodeBaseProxy>(new KeyProvider());

		for (TemplateTreeNodeBaseProxy base : root.getChildren()) {
			store.add(base);
			if (base instanceof TemplateTreeNodeFolderProxy) {
				processFolder(store, (TemplateTreeNodeFolderProxy) base);
			}
		}

		createTree(store);

		container.add(tree);

		container.setCellWidth(tree, "260px");

		createEditContainer();

		return container;
	}

	private void createEditContainer() {
		VerticalPanel editContainer = new VerticalPanel();
		container.add(editContainer);

		infoContainer = new TemplateTreeNodeInfo();
		editContainer.add(infoContainer);

		HorizontalPanel buttonsContainer = new HorizontalPanel();
		buttonsContainer.setSpacing(10);
		editContainer.add(buttonsContainer);

		TextButton addNodeButton = new TextButton("Добавить узел");
		addNodeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final TemplateTreeNodeBaseProxy item = tree.getSelectionModel().getSelectedItem();
				long parentId;
				if (item == null) {
					parentId = root.getId();
				} else {
					parentId = item.getId();
				}
				TemplateModuleView.getService().createNewTemplateTreeNode(parentId, new AsyncCallbackWithErrorMessage<TemplateTreeNodeBaseProxy>() {

					@Override
					public void onSuccess(TemplateTreeNodeBaseProxy result) {
						if (item != null) {
							tree.getStore().add(item, result);
						} else {
							tree.getStore().add(result);
						}
					}
				});
			}
		});
		buttonsContainer.add(addNodeButton);

		TextButton deleteNodeButton = new TextButton("Удалить узел");
		deleteNodeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final TemplateTreeNodeBaseProxy item = tree.getSelectionModel().getSelectedItem();
				if (item == null) {
					Info.display("Error", "Не выбрал удел для удаления");
					return;
				}
				TemplateModuleView.getService().deleteTemplateTreeNode(item.getId(), new AsyncCallbackWithErrorMessage<Void>() {
					@Override
					public void onSuccess(Void result) {
						tree.getStore().remove(item);
					}
				});
			}
		});
		buttonsContainer.add(deleteNodeButton);

		TextButton save = new TextButton("Сохранить");
		save.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TemplateTreeNodeBaseProxy item = tree.getSelectionModel().getSelectedItem();
				if (item == null) {
					Info.display("Error", "Не выбран удел для сохранения");
					return;
				}
				infoContainer.saveValue(item);
				tree.refresh(item);
				TemplateModuleView.getService().save(item, new AsyncCallbackWithErrorMessage<TemplateTreeNodeBaseProxy>() {
					@Override
					public void onSuccess(TemplateTreeNodeBaseProxy result) {
						Info.display("сохранение", "Сохранения произведено");
					}
				});
			}
		});
		buttonsContainer.add(save);

	}

	private void createTree(TreeStore<TemplateTreeNodeBaseProxy> store) {
		tree = new Tree<TemplateTreeNodeBaseProxy, String>(store, new ValueProvider<TemplateTreeNodeBaseProxy, String>() {

			@Override
			public String getValue(TemplateTreeNodeBaseProxy node) {
				return node.getName() + " (" + node.getDuration() + " дн.)";
			}

			@Override
			public void setValue(TemplateTreeNodeBaseProxy object, String value) {
			}

			@Override
			public String getPath() {
				return "name";
			}
		});
		tree.setWidth(300);

		tree.setIconProvider(new TreeIconProvider<TemplateTreeNodeBaseProxy>());

		tree.getSelectionModel().addSelectionHandler(new SelectionHandler<TemplateTreeNodeBaseProxy>() {

			@Override
			public void onSelection(SelectionEvent<TemplateTreeNodeBaseProxy> event) {
				infoContainer.setValue(event.getSelectedItem());
			}
		});
	}

	private static void processFolder(TreeStore<TemplateTreeNodeBaseProxy> store, TemplateTreeNodeFolderProxy folder) {
		for (TemplateTreeNodeBaseProxy child : folder.getChildren()) {
			store.add(folder, child);
			if (child instanceof TemplateTreeNodeFolderProxy) {
				processFolder(store, (TemplateTreeNodeFolderProxy) child);
			}
		}
	}

	private static class KeyProvider implements ModelKeyProvider<TemplateTreeNodeBaseProxy> {
		@Override
		public String getKey(TemplateTreeNodeBaseProxy item) {
			return (item instanceof TemplateTreeNodeFolderProxy ? "f-" : "m-") + item.getId();
		}
	}

}
