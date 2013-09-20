package ru.kmz.web.template.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeNodeBaseProxy implements Serializable {

	private String id;

	private String name;

	public TemplateTreeNodeBaseProxy() {

	}

	public TemplateTreeNodeBaseProxy(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
