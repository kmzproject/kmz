package ru.kmz.web.templatecommon.shared;

import java.io.Serializable;


@SuppressWarnings("serial")
public class TemplateTreeDataProxy implements Serializable {

	private String id;
	private TemplateTreeNodeFolderProxy root;

	private String name;

	public TemplateTreeNodeFolderProxy getTreeRoot() {
		return root;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public TemplateTreeDataProxy() {
	}

	public TemplateTreeDataProxy(String id, String name, TemplateTreeNodeFolderProxy root) {
		this.id = id;
		this.name = name;
		this.root = root;
	}
}
