package ru.kmz.web.template.client;

import ru.kmz.web.common.client.TreeIconProvider;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class TemplateTree implements IsWidget {

	private TemplateTreeNodeFolderProxy root;

	public void setRoot(TemplateTreeNodeFolderProxy root) {
		this.root = root;
	}

	private HorizontalPanel container;
	private TemplateTreeNodeInfo infoContainer;

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

		final Tree<TemplateTreeNodeBaseProxy, String> tree = getTree(store);

		container.add(tree);

		container.setCellWidth(tree, "260px");

		infoContainer = new TemplateTreeNodeInfo();

		container.add(infoContainer);

		return container;
	}

	private Tree<TemplateTreeNodeBaseProxy, String> getTree(TreeStore<TemplateTreeNodeBaseProxy> store) {
		final Tree<TemplateTreeNodeBaseProxy, String> tree = new Tree<TemplateTreeNodeBaseProxy, String>(store,
				new ValueProvider<TemplateTreeNodeBaseProxy, String>() {

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
		return tree;
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
