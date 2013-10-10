package ru.kmz.web.ordercommon.client;

import java.util.List;

import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("orderCommon")
public interface OrderCommonService extends RemoteService {

	List<OrderProxy> getOrders();

}
