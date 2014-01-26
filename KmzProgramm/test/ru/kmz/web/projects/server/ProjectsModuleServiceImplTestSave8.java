package ru.kmz.web.projects.server;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.common.server.CommonServiceImpl;
import ru.kmz.web.common.shared.HistoryProxy;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.test.DataTestEveryNew;

public class ProjectsModuleServiceImplTestSave8 extends DataTestEveryNew {

	private ProjectsModuleServiceImpl service;
	private CommonServiceImpl commonService;

	@Before
	public void createService() {
		service = new ProjectsModuleServiceImpl();
		commonService = new CommonServiceImpl();
	}

	@Test
	public void getGantResultDataTest1Resrource() {
		Template template = TemplateTestData.createTemplateShort8();
		Order order = OrderTestData.createOrders1().get(0);
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getId());
		long orderId = order.getId();
		input.setOrderId(orderId);
		service.save(input);

		order = OrderDataUtils.getOrderAndLoadAllChild(orderId);

		Assert.assertEquals(1, order.getChilds().size());
		Assert.assertEquals("Изделие", order.getChilds().get(0).getName());
		Assert.assertEquals(ResourceTypes.PRODUCT, order.getChilds().get(0).getResourceType());
		Assert.assertEquals(date, order.getChilds().get(0).getFinish());
		Assert.assertEquals(4, order.getChilds().get(0).getChilds().size());

		List<HistoryProxy> history = commonService.getHistoryByObject(null);
		Assert.assertEquals(1, history.size());
		Assert.assertEquals("I-001001 Изделие добавлено в производство", history.get(0).getComment());

	}

}
