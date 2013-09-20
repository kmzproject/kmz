package ru.kmz.web.template.shared;

public class TemplateTreeNodeBaseProxy {

	private String id;

	private String name;
	
	public TemplateTreeNodeBaseProxy(String id, String name){
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
