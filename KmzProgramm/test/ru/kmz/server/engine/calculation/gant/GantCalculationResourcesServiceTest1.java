package ru.kmz.server.engine.calculation.gant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.test.DataTestEveryNew;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.GraphData;

public class GantCalculationResourcesServiceTest1 extends DataTestEveryNew {

	@Test
	public void Test1() {
		Date start = DateUtils.getDate("2013/10/01");
		GantCalculationResourcesService service = new GantCalculationResourcesService();
		service.setResourcesList(getResources());
		service.calculateStart(getTemplate(), start);
		GanttData data = service.getGantData();

		Assert.assertEquals(data.getChilds().size(), 1);

		GraphData rootWbs = data.getChilds().get(0);
		Assert.assertEquals(rootWbs.getChilds().size(), 1);

		List<GraphData> wbs = rootWbs.getChilds().get(0).getChilds();
		Assert.assertEquals(wbs.size(), 1);

		Assert.assertEquals(start, data.getDateStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 13), data.getDateFinish());

		Assert.assertEquals(start, wbs.get(0).getPlanStart());
		Assert.assertEquals(CalculationUtils.getOffsetDate(start, 10), wbs.get(0).getPlanFinish());
	}

	private Template getTemplate() {
		Template tempalte = TemplateTestData.createTemplateShort3();
		tempalte = TemplateDataUtils.getTemplate(tempalte.getKeyStr());
		return tempalte;
	}

	private List<Resource> getResources() {
		List<Resource> list = new ArrayList<Resource>();
		list.add(new Resource("test1", ResourceTypes.PREPARE));
		return list;
	}

}
