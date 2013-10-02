package ru.kmz.server.engine.calculation.gant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.web.ganttcommon.shared.ActivityData;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.WbsData;

public class GantCalculationResourcesServiceTest1 {

	@Test
	public void Test1() {
		Date start = DateUtils.getDate("2013/10/01");
		GantCalculationResourcesService service = new GantCalculationResourcesService();
		service.setResourcesList(getResources());
		service.calculateStart(getTemplate(), start);
		GanttData data = service.getGantData();

		Assert.assertEquals(data.getWbss().size(), 1);

		WbsData rootWbs = data.getWbss().get(0);
		Assert.assertEquals(rootWbs.getActivities().size(), 3);

		List<ActivityData> activities = rootWbs.getActivities();
		Assert.assertEquals(activities.get(0).getPlanStart(), start);
		Assert.assertEquals(activities.get(0).getPlanFinish(), CalculationUtils.getOffsetDate(start, 10));

		Assert.assertEquals(activities.get(1).getPlanStart(), CalculationUtils.getOffsetDate(start, 11));
		Assert.assertEquals(activities.get(1).getPlanFinish(), CalculationUtils.getOffsetDate(start, 21));

		Assert.assertEquals(activities.get(2).getPlanStart(), CalculationUtils.getOffsetDate(start, 22));
		Assert.assertEquals(activities.get(2).getPlanFinish(), CalculationUtils.getOffsetDate(start, 32));

		Assert.assertEquals(data.getDateStart(), start);
		Assert.assertEquals(data.getDateFinish(), CalculationUtils.getOffsetDate(start, 32));
	}

	private Template getTemplate() {
		Template template = new Template();
		ProducteTemplate producte = new ProducteTemplate();
		template.setProduct(producte);

		producte.add(new ProducteTemplateElement("test1", 10, ResourceTypes.PREPARE));
		producte.add(new ProducteTemplateElement("test2", 10, ResourceTypes.PREPARE));
		producte.add(new ProducteTemplateElement("test3", 10, ResourceTypes.PREPARE));
		return template;
	}

	private List<Resource> getResources() {
		List<Resource> list = new ArrayList<Resource>();
		list.add(new Resource("test1", ResourceTypes.PREPARE));
		return list;
	}

}