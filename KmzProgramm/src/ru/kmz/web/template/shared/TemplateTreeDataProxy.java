package ru.kmz.web.template.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeDataProxy implements Serializable {

	private long id;
	private TemplateTreeNodeFolderProxy root;

	private String name;

	public TemplateTreeNodeFolderProxy getTreeRoot() {
		return root;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public TemplateTreeDataProxy() {
	}

	public TemplateTreeDataProxy(long id, String name, TemplateTreeNodeFolderProxy root) {
		this.id = id;
		this.name = name;
		this.root = root;
	}
}
