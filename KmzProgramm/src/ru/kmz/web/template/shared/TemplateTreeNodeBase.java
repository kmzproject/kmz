package ru.kmz.web.template.shared;

public class TemplateTreeNodeBase {

	private String id;

	private String name;
	
	public TemplateTreeNodeBase(String id, String name){
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
