package ru.kmz.web.projects.server;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.server.data.generator.TemplateTestData;
import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.test.DataTestEveryNew;

public class ProjectsModuleServiceImplTestSave8 extends DataTestEveryNew {

	@Test
	public void getGantResultDataTest1Resrource() {
		Template template = TemplateTestData.createTemplateShort8();
		Order order = OrderTestData.createOrders1().get(0);
		ProjectsModuleServiceImpl service = new ProjectsModuleServiceImpl();
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		Date date = DateUtils.getDate("2013/10/01");
		input.setDate(date);
		input.setTemplateId(template.getKeyStr());
		input.setByStartDate(true);
		input.setUseResource(false);
		String orderId = order.getKeyStr();
		service.save(input, orderId);

		order = OrderDataUtils.getOrder(orderId);

		Assert.assertEquals(1, order.getChilds().size());
		Assert.assertEquals("Изделие", order.getChilds().get(0).getName());
		Assert.assertEquals(date, order.getChilds().get(0).getFinish());
		Assert.assertEquals(4, order.getChilds().get(0).getChilds().size());
	}

}
