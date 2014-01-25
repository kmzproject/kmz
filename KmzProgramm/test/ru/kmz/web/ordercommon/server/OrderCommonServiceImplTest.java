package ru.kmz.web.ordercommon.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.generator.OrderTestData;
import ru.kmz.web.common.client.service.CommonService;
import ru.kmz.web.common.server.CommonServiceImpl;
import ru.kmz.web.common.shared.HistoryProxy;
import ru.kmz.web.ordercommon.shared.OrderProxy;
import ru.test.DataTestEveryNew;

public class OrderCommonServiceImplTest extends DataTestEveryNew {

	private OrderCommonServiceImpl service;
	private CommonService commonService;

	@Before
	public void createService() {
		service = new OrderCommonServiceImpl();
		commonService = new CommonServiceImpl();
	}

	@Test
	public void getDateTest1() {

		OrderTestData.createOrders1();
		List<OrderProxy> list = service.getOrders();
		Assert.assertEquals(1, list.size());

		Assert.assertEquals("Z-001", list.get(0).getCode());

	}

	@Test
	public void createOrder1() {
		OrderProxy proxy = new OrderProxy();
		proxy.setLegalNumber("legal1");
		proxy.setCustomer("customer");
		proxy.setName("name1");
		service.editOrder(proxy);

		List<OrderProxy> list = service.getOrders();
		Assert.assertEquals(1, list.size());

		Assert.assertEquals("name1", list.get(0).getName());
		Assert.assertEquals("Z-001", list.get(0).getCode());

		proxy.setName("name2");
		proxy = service.editOrder(proxy);

		proxy.setName("name2-1");
		service.editOrder(proxy);

		list = service.getOrders();
		Assert.assertEquals(2, list.size());
		Assert.assertEquals("name1", list.get(0).getName());
		Assert.assertEquals("Z-001", list.get(0).getCode());

		Assert.assertEquals("name2-1", list.get(1).getName());
		Assert.assertEquals("Z-002", list.get(1).getCode());

		List<HistoryProxy> history = commonService.getHistoryByObject(null);
		Assert.assertEquals(3, history.size());
	}

	@Test
	public void deleteEmptyOrder() {
		OrderProxy proxy = new OrderProxy();
		proxy.setLegalNumber("legal1");
		proxy.setCustomer("customer");
		proxy.setName("name1");
		proxy = service.editOrder(proxy);

		service.deleteOrder(proxy.getId());
		List<OrderProxy> list = service.getOrders();

		Assert.assertEquals(0, list.size());
	}
}
