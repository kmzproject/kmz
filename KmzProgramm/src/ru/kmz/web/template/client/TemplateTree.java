package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeNodeBase;
import ru.kmz.web.template.shared.TemplateTreeNodeFolder;

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

	private TemplateTreeNodeFolder root;

	public void setRoot(TemplateTreeNodeFolder root) {
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

		TreeStore<TemplateTreeNodeBase> store = new TreeStore<TemplateTreeNodeBase>(new KeyProvider());

		for (TemplateTreeNodeBase base : root.getChildren()) {
			store.add(base);
			if (base instanceof TemplateTreeNodeFolder) {
				processFolder(store, (TemplateTreeNodeFolder) base);
			}
		}

		final Tree<TemplateTreeNodeBase, String> tree = getTree(store);

		container.add(tree);

		container.setCellWidth(tree, "260px");

		infoContainer = new TemplateTreeNodeInfo();

		container.add(infoContainer);

		return container;
	}

	private Tree<TemplateTreeNodeBase, String> getTree(TreeStore<TemplateTreeNodeBase> store) {
		final Tree<TemplateTreeNodeBase, String> tree = new Tree<TemplateTreeNodeBase, String>(store,
				new ValueProvider<TemplateTreeNodeBase, String>() {

					@Override
					public String getValue(TemplateTreeNodeBase object) {
						return object.getName();
					}

					@Override
					public void setValue(TemplateTreeNodeBase object, String value) {
					}

					@Override
					public String getPath() {
						return "name";
					}
				});
		tree.setWidth(300);
		// tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());

		tree.getSelectionModel().addSelectionHandler(new SelectionHandler<TemplateTreeNodeBase>() {

			@Override
			public void onSelection(SelectionEvent<TemplateTreeNodeBase> event) {
				infoContainer.setValue(event.getSelectedItem());
			}
		});
		return tree;
	}

	private static void processFolder(TreeStore<TemplateTreeNodeBase> store, TemplateTreeNodeFolder folder) {
		for (TemplateTreeNodeBase child : folder.getChildren()) {
			store.add(folder, child);
			if (child instanceof TemplateTreeNodeFolder) {
				processFolder(store, (TemplateTreeNodeFolder) child);
			}
		}
	}

	private static class KeyProvider implements ModelKeyProvider<TemplateTreeNodeBase> {
		@Override
		public String getKey(TemplateTreeNodeBase item) {
			return (item instanceof TemplateTreeNodeFolder ? "f-" : "m-") + item.getId();
		}
	}

}
