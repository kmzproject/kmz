package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class TemplateModuleView implements EntryPoint {

	@Override
	public void onModuleLoad() {
		TemplateTree tree = new TemplateTree();
		TemplateTreeData data = new TemplateTreeData();
		tree.setRoot(data.getTreeRoot());
		RootPanel.get("tree").add(tree);
	}

}
