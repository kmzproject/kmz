package ru.kmz.web.template.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeDataProxy implements Serializable {

	private TemplateTreeNodeFolderProxy root;

	public TemplateTreeNodeFolderProxy getTreeRoot() {
		return root;
	}

	public TemplateTreeDataProxy() {
	}

	public TemplateTreeDataProxy(TemplateTreeNodeFolderProxy root) {
		this.root = root;
	}
}
