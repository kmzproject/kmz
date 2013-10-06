package ru.kmz.server.engine.calculation.resources;

import java.util.HashMap;
import java.util.Map;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;

public class ResourceMap {

	private Map<ProducteTemplateElement, Resource> map;

	public ResourceMap() {
		map = new HashMap<ProducteTemplateElement, Resource>();
	}

	public ResourceMap(ResourceMap m) {
		map = new HashMap<ProducteTemplateElement, Resource>();
		for (ProducteTemplateElement element : m.map.keySet()) {
			map.put(element, m.map.get(element));
		}
	}

	public void put(ProducteTemplateElement element, Resource resource) {
		map.put(element, resource);
	}

	public void print() {
		for (ProducteTemplateElement element : map.keySet()) {
			System.out.println(element.getName() + " " + map.get(element).toString());
		}
	}

	public Map<ProducteTemplateElement, Resource> getMap() {
		return map;
	}
}
