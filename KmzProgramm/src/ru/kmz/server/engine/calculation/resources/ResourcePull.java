package ru.kmz.server.engine.calculation.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kmz.server.data.model.Resource;

public class ResourcePull {

	private Map<Resource, List<ResourceTask>> pull;

	public ResourcePull() {
		pull = new HashMap<Resource, List<ResourceTask>>();
	}

	public void init(List<Resource> resources) {
		for (Resource resource : resources) {
			if (!resource.getResourceType().equals(resource.getResourceType()))
				continue;
			List<ResourceTask> shaduler = new ArrayList<ResourceTask>();
			pull.put(resource, shaduler);
		}
	}

	public void printPull() {
		for (Resource resource : pull.keySet()) {
			System.out.println(resource.toString());
			for (ResourceTask task : pull.get(resource)) {
				System.out.println("\t" + task.toString());
			}
		}
	}

}
