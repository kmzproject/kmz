package ru.kmz.web.ordercommon.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.web.ordercommon.shared.OrderProxy;
import ru.test.DataTestEveryNew;

public class OrderCommonServiceImplTest extends DataTestEveryNew {

	private OrderCommonServiceImpl service;

	@Before
	public void createService() {
		service = new OrderCommonServiceImpl();
	}

	@Test
	public void getDateTest1() {

		OrderTestData.createOrders1();
		List<OrderProxy> list = service.getOrders();
		Assert.assertEquals(1, list.size());

	}

}
