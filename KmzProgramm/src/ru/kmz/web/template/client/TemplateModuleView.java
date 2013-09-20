package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class TemplateModuleView implements EntryPoint {

	@Override
	public void onModuleLoad() {
		TemplateTree tree = new TemplateTree();
		TemplateTreeDataProxy data = new TemplateTreeDataProxy();
		tree.setRoot(data.getTreeRoot());
		RootPanel.get("tree").add(tree);
	}

}
