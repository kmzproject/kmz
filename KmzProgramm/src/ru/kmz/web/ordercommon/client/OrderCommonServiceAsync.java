package ru.kmz.web.ordercommon.client;

import java.util.List;

import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrderCommonServiceAsync {

	void getOrders(AsyncCallback<List<OrderProxy>> callback);

	void editOrder(OrderProxy proxy, AsyncCallback<OrderProxy> callback);

	void deleteOrder(long id, AsyncCallback<Void> callback);

}
