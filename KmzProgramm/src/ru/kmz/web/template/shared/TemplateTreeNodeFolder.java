package ru.kmz.web.template.shared;

import java.util.ArrayList;
import java.util.List;

public class TemplateTreeNodeFolder extends TemplateTreeNodeBase {

	List<TemplateTreeNodeBase> list;

	public TemplateTreeNodeFolder(String id, String name) {
		super(id, name);
		list = new ArrayList<TemplateTreeNodeBase>();
	}

	public List<TemplateTreeNodeBase> getChildren() {
		return list;
	}

	public void add(TemplateTreeNodeBase item) {
		list.add(item);
	}

}
