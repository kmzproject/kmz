package ru.kmz.server.engine.calculation.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.web.common.shared.ResourceTypesConsts;

public class CalculateExecutaionFirstFreeService {

	private ResourcePull pull;
	private Map<ProducteTemplateElement, ResourceTask> result;

	public CalculateExecutaionFirstFreeService(List<Resource> resources) {
		result = new HashMap<ProducteTemplateElement, ResourceTask>();
		pull = new ResourcePull();
		pull.init(resources);
	}

	public Date calculateElementFinish(ProducteTemplateElement element, Date start) {
		Date maxChildFinish = start;
		if (element.hasChild()) {
			maxChildFinish = addChilds(element, start);
		}
		ResourceTask task;
		String resourceType = element.getResourceType();
		if (ResourceTypesConsts.needResource(resourceType)) {
			task = pull.getFirstFreeTask(element, maxChildFinish);
		} else {
			task = new ResourceTask(maxChildFinish, element.getDuration());
		}

		if (element.hasChild()) {
			correctChild(element, task.getStart());
		}

		result.put(element, task);
		return task.getFinish();
	}

	private Date addChilds(ProducteTemplateElement element, Date start) {
		Date maxChildFinish = start;
		for (ProducteTemplateElement e : element.getChilds()) {
			Date childFinish = calculateElementFinish(e, start);
			if (maxChildFinish.before(childFinish)) {
				maxChildFinish = childFinish;
			}
		}
		return maxChildFinish;
	}

	private void correctChild(ProducteTemplateElement element, Date start) {
		for (ProducteTemplateElement e : element.getChilds()) {
			if (ResourceTypesConsts.startAsLateAsPossible(e.getResourceType())) {
				System.out.println(">>>+++++");
				ResourceTask childTask = result.get(e);
				System.out.println("childTask1=" + childTask + " maxChildFinish=" + start);
				childTask.toFinish(start);
				System.out.println("childTask2=" + childTask);
				System.out.println("childTask3=" + result.get(e));
				System.out.println("<<<+++++");
			}
		}
	}

	public Map<ProducteTemplateElement, ResourceTask> getResult() {
		return result;
	}
}
