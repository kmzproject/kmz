package ru.kmz.web.template.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TemplateTreeNodeBaseProxy implements Serializable {

	private String id;

	private String name;

	private int duration;

	public TemplateTreeNodeBaseProxy() {

	}

	public TemplateTreeNodeBaseProxy(String id, String name, int duration) {
		this.id = id;
		this.name = name;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

}
