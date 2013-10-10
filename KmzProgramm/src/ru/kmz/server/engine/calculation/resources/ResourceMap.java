package ru.kmz.server.engine.calculation.resources;

import java.util.HashMap;
import java.util.Map;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Resource;

public class ResourceMap {

	private Map<ProductTemplateElement, Resource> map;

	public ResourceMap() {
		map = new HashMap<ProductTemplateElement, Resource>();
	}

	public ResourceMap(ResourceMap m) {
		map = new HashMap<ProductTemplateElement, Resource>();
		for (ProductTemplateElement element : m.map.keySet()) {
			map.put(element, m.map.get(element));
		}
	}

	public void put(ProductTemplateElement element, Resource resource) {
		map.put(element, resource);
	}

	public void print() {
		for (ProductTemplateElement element : map.keySet()) {
			System.out.println(element.getName() + " " + map.get(element).toString());
		}
	}

	public Map<ProductTemplateElement, Resource> getMap() {
		return map;
	}
}
