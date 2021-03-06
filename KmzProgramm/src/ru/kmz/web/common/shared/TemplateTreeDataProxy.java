package ru.kmz.web.common.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeDataProxy implements Serializable, IHasIdProxy {

	private long id;
	private TemplateTreeNodeFolderProxy root;

	private String name;

	public TemplateTreeNodeFolderProxy getTreeRoot() {
		return root;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
