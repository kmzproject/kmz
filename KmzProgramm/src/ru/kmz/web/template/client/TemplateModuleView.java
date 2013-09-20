package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateModuleView implements EntryPoint {

	private final TemplateModuleServiceAsync templateMpduleService = GWT.create(TemplateModuleService.class);

	@Override
	public void onModuleLoad() {
		templateMpduleService.getData(new AsyncCallback<TemplateTreeDataProxy>() {

			@Override
			public void onSuccess(TemplateTreeDataProxy result) {
				TemplateTree tree = new TemplateTree();
				tree.setRoot(result.getTreeRoot());
				RootPanel.get("tree").add(tree);
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "Load error");
			}
		});
	}

}
