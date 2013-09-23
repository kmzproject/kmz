package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.ResourceTypesConsts;
import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class TemplateTree implements IsWidget {

	public static interface Images extends ClientBundle {
		public Images INSTANCE = GWT.create(Images.class);

		@Source("order.png")
		ImageResource order();

		@Source("prepare.png")
		ImageResource prepare();

		@Source("assemblage.png")
		ImageResource assemblage();
	}

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
		// tree.getStyle().setLeafIcon(ExampleImages.INSTANCE.music());

		tree.setIconProvider(new IconProvider<TemplateTreeNodeBaseProxy>() {

			@Override
			public ImageResource getIcon(TemplateTreeNodeBaseProxy node) {
				if (node.getResourceType().equals(ResourceTypesConsts.ASSEMBLAGE))
					return Images.INSTANCE.assemblage();
				if (node.getResourceType().equals(ResourceTypesConsts.PREPARE))
					return Images.INSTANCE.prepare();
				if (node.getResourceType().equals(ResourceTypesConsts.ORDER))
					return Images.INSTANCE.order();
				return Images.INSTANCE.order();
			}
		});

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
