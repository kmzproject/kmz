package ru.kmz.web.template.shared;

import java.util.ArrayList;
import java.util.List;

public class TemplateTreeNodeFolderProxy extends TemplateTreeNodeBaseProxy {

	List<TemplateTreeNodeBaseProxy> list;

	public TemplateTreeNodeFolderProxy(String id, String name) {
		super(id, name);
		list = new ArrayList<TemplateTreeNodeBaseProxy>();
	}

	public List<TemplateTreeNodeBaseProxy> getChildren() {
		return list;
	}

	public void add(TemplateTreeNodeBaseProxy item) {
		list.add(item);
	}

}
