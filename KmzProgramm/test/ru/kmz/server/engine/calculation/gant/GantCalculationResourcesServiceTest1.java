package ru.kmz.server.engine.calculation.gant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class GantCalculationResourcesServiceTest1 {

	@Test
	public void Test1() {
		Date start = DateUtils.getDate("2013/10/01");
		GantCalculationResourcesService service = new GantCalculationResourcesService();
		service.setResourcesList(getResources());
		service.calculateStart(getTemplate(), start);
		GanttData data = service.getGantData();

		Assert.assertEquals(data.getChilds().size(), 1);

		GraphData rootWbs = data.getChilds().get(0);
		Assert.assertEquals(rootWbs.getChilds().size(), 3);

		List<GraphData> activities = rootWbs.getChilds();
		Assert.assertEquals(activities.get(0).getPlanStart(), start);
		Assert.assertEquals(activities.get(0).getPlanFinish(), CalculationUtils.getOffsetDate(start, 10));

		Assert.assertEquals(activities.get(1).getPlanStart(), CalculationUtils.getOffsetDate(start, 11));
		Assert.assertEquals(activities.get(1).getPlanFinish(), CalculationUtils.getOffsetDate(start, 21));

		Assert.assertEquals(activities.get(2).getPlanStart(), CalculationUtils.getOffsetDate(start, 22));
		Assert.assertEquals(activities.get(2).getPlanFinish(), CalculationUtils.getOffsetDate(start, 32));

		Assert.assertEquals(data.getDateStart(), start);
		Assert.assertEquals(data.getDateFinish(), CalculationUtils.getOffsetDate(start, 32));
		Assert.assertEquals(rootWbs.getPlanStart(), start);
		Assert.assertEquals(rootWbs.getPlanFinish(), CalculationUtils.getOffsetDate(start, 32));
		Assert.assertEquals(rootWbs.getDuration(), 32);
	}

	private Template getTemplate() {
		// Template template = new Template();
		// ProducteTemplate producte = new ProducteTemplate();
		// template.setProduct(producte);
		//
		// producte.add(new ProducteTemplateElement("test1", 10,
		// ResourceTypes.PREPARE));
		// producte.add(new ProducteTemplateElement("test2", 10,
		// ResourceTypes.PREPARE));
		// producte.add(new ProducteTemplateElement("test3", 10,
		// ResourceTypes.PREPARE));
		// return template;
		return null;
	}

	private List<Resource> getResources() {
		List<Resource> list = new ArrayList<Resource>();
		list.add(new Resource("test1", ResourceTypes.PREPARE));
		return list;
	}

}
