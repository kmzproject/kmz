package ru.kmz.server.engine.calculation.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;

public class ResourceMapGenerator {

	private Map<String, List<Resource>> rMap;
	private List<ResourceMap> maps;
	private List<ProducteTemplateElement> elements;

	public ResourceMapGenerator(List<Resource> resources, List<ProducteTemplateElement> elements) {
		rMap = new HashMap<String, List<Resource>>();
		for (Resource resource : resources) {
			if (!rMap.containsKey(resource.getResourceType())) {
				List<Resource> list = new ArrayList<Resource>();
				rMap.put(resource.getResourceType(), list);
			}
			rMap.get(resource.getResourceType()).add(resource);
		}
		maps = new ArrayList<ResourceMap>();
		this.elements = elements;
	}

	public List<ResourceMap> getResourceMaps() {
		return maps;
	}

	public void generate() {
		fillResourceMaps(0, new ResourceMap());
	}

	private void fillResourceMaps(int index, ResourceMap map) {
		if (index == elements.size()) {
			maps.add(map);
			return;
		}
		ProducteTemplateElement element = elements.get(index);
		List<Resource> r = rMap.get(element.getResourceType());
		for (Resource resource : r) {
			ResourceMap m = new ResourceMap(map);
			m.put(element, resource);
			fillResourceMaps(index + 1, m);
		}
	}
}
