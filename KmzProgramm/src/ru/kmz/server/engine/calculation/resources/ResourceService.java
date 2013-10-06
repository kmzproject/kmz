package ru.kmz.server.engine.calculation.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kmz.server.data.model.Resource;
import ru.kmz.server.engine.calculation.CalculationUtils;

public class ResourceService {

	private Map<Resource, List<ResourceTask>> pull;

	public ResourceService() {
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

	// TODO пока что считаем что мы не разбиваем производство одной детали на
	// части, так что бы можно было сделать часть до какой-то работы а часть
	// после, так жа не считаем что можно разбить выполнение задачи на разные
	// ресурсы
	public ResourceTask getResentTask(String resourceType, Date start, int duration) {
		Resource allowResource = null;
		Date allowStart = null;
		for (Resource resource : pull.keySet()) {
			if (!resource.getResourceType().equals(resourceType))
				continue;
			Date allowStartByCurrentResource = getFirstFreeTime(pull.get(resource), start, duration);
			if (allowStart == null || allowStartByCurrentResource.before(allowStart)) {
				allowResource = resource;
				allowStart = allowStartByCurrentResource;
			}
		}
		ResourceTask task = new ResourceTask(allowStart, duration);
		insertResourceTask(pull.get(allowResource), task);
		return task;
	}

	// Работате только если список отсортирован по доте начала от меньшей к
	// большей
	private Date getFirstFreeTime(List<ResourceTask> tasks, Date start, int duration) {
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

	private void insertResourceTask(List<ResourceTask> tasks, ResourceTask task) {
		tasks.add(task);
		Collections.sort(tasks);
		this.printPull();
	}
}
