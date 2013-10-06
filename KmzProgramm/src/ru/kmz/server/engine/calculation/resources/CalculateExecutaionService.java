package ru.kmz.server.engine.calculation.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;

public class CalculateExecutaionService {

	private Date startProcess;
	private Resource resources;
	private Map<ProducteTemplateElement, ResourceTask> result;

	public CalculateExecutaionService(Date start, List<Resource> resources) {
		this.startProcess = start;
		result = new HashMap<ProducteTemplateElement, ResourceTask>();
	}

	public Date calculateElementFinish(ProducteTemplateElement element) {
		if (element.hasChild()) {

		}
		return null;
	}

	public Map<ProducteTemplateElement, ResourceTask> getResult() {
		return result;
	}
}
