package ru.kmz.web.ordercommon.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.ordercommon.client.OrderCommonService;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class OrderCommonServiceImpl extends RemoteServiceServlet implements Controller, ServletContextAware, OrderCommonService {
	public List<OrderProxy> getOrders() {
		List<Order> orders = OrderDataUtils.getAllOrders();
		List<OrderProxy> result = new ArrayList<OrderProxy>();
		for (Order order : orders) {
			result.add(order.asProxy());
		}
		return result;
	}

	@Override
	public OrderProxy editOrder(OrderProxy proxy) {
		boolean isNew = proxy.getId() == null;
		Order order;
		if (isNew) {
			order = new Order();
		} else {
			order = OrderDataUtils.getOrder(proxy.getId());
		}
		order.updateData(proxy);
		order = OrderDataUtils.edit(order);
		proxy = order.asProxy();
		if (isNew) {
			HistoryUtils.createOrder(order);
		} else {
			HistoryUtils.editOrder(order);
		}
		return proxy;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		return null;
	}

	private ServletContext servletContext;

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
