package ru.kmz.server.engine.calculation.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.engine.calculation.CalculationUtils;

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

	public ResourceTask getFirstFreeTask(ProductTemplateElement element, Date start) {
		Resource freeResource = null;
		Date minFreeResourceStart = null;
		for (Resource r : pull.keySet()) {
			if (r.getResourceType().equals(element.getResourceType())) {
				Date freeResourceStart = getFirstFreeTaskInList(pull.get(r), start, element.getDuration());
				if (minFreeResourceStart == null || minFreeResourceStart.after(freeResourceStart)) {
					minFreeResourceStart = freeResourceStart;
					freeResource = r;
				}
			}
		}
		ResourceTask task = new ResourceTask(freeResource, minFreeResourceStart, element.getDuration());
		insertResourceTask(task);
		return task;
	}

	private Date getFirstFreeTaskInList(List<ResourceTask> tasks, Date start, int duration) {
		Date allowStart = start;
		for (ResourceTask resourceTask : tasks) {
			if (resourceTask.getFinish().before(allowStart)) {
				continue;
			}
			Date allowFinish = CalculationUtils.getOffsetDate(allowStart, duration);
			if (resourceTask.getStart().before(allowFinish)) {
				allowStart = resourceTask.getFinish();
				continue;
			}
			break;
		}
		return allowStart;
	}

	private void insertResourceTask(ResourceTask task) {
		List<ResourceTask> tasks = pull.get(task.getResource());
		tasks.add(task);
		Collections.sort(tasks);
		this.printPull();
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
