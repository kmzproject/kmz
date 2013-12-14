package ru.kmz.web.common.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TemplateTreeNodeFolderProxy extends TemplateTreeNodeBaseProxy implements Serializable {

	List<TemplateTreeNodeBaseProxy> list;

	public TemplateTreeNodeFolderProxy() {
	};

	public TemplateTreeNodeFolderProxy(long id, String name, int duration, String resourceType) {
		super(id, name, duration, resourceType);
		list = new ArrayList<TemplateTreeNodeBaseProxy>();
	}

	public List<TemplateTreeNodeBaseProxy> getChildren() {
		return list;
	}

	public void add(TemplateTreeNodeBaseProxy item) {
		list.add(item);
	}

}
