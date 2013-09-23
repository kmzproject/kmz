package ru.kmz.web.template.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeDataProxy implements Serializable {

	private TemplateTreeNodeFolderProxy root;

	private String name;

	public TemplateTreeNodeFolderProxy getTreeRoot() {
		return root;
	}

	public String getName() {
		return name;
	}

	public TemplateTreeDataProxy() {
	}

	public TemplateTreeDataProxy(String name, TemplateTreeNodeFolderProxy root) {
		this.name = name;
		this.root = root;
	}
}
