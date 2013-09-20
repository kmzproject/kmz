package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeNodeBase;
import ru.kmz.web.template.shared.TemplateTreeNodeFolder;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class TemplateTree implements IsWidget {

	private TemplateTreeNodeFolder root;

	public void setRoot(TemplateTreeNodeFolder root){
		this.root = root;
	}
	
	@Override
	public Widget asWidget() {
		FlowLayoutContainer con = new FlowLayoutContainer();
		con.addStyleName("margin-10");

		TreeStore<TemplateTreeNodeBase> store = new TreeStore<TemplateTreeNodeBase>(new KeyProvider());

		for (TemplateTreeNodeBase base : root.getChildren()) {
			store.add(base);
			if (base instanceof TemplateTreeNodeFolder) {
				processFolder(store, (TemplateTreeNodeFolder) base);
			}
		}

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

		ButtonBar buttonBar = new ButtonBar();

		buttonBar.add(new TextButton("Показать все", new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				tree.expandAll();
			}
		}));

		buttonBar.add(new TextButton("Скрыть", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				tree.collapseAll();
			}

		}));

		buttonBar.setLayoutData(new MarginData(4));
		con.add(buttonBar);

		con.add(tree);
		return con;
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
