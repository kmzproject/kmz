package ru.kmz.web.order.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.client.OrderProxyProperties;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class OrdersGrid extends CommonGrid<OrderProxy> {

	private static final OrderProxyProperties props;

	static {
		props = GWT.create(OrderProxyProperties.class);
	}

	public static OrdersGrid getOrderGrid() {
		ListStore<OrderProxy> store = new ListStore<OrderProxy>(props.key());

		ColumnConfig<OrderProxy, String> codeCol = new ColumnConfig<OrderProxy, String>(props.code(), 50, "Код");
		ColumnConfig<OrderProxy, String> nameCol = new ColumnConfig<OrderProxy, String>(props.name(), 200, "Название заказа");
		ColumnConfig<OrderProxy, String> customerCol = new ColumnConfig<OrderProxy, String>(props.customer(), 200, "Заказчик");
		ColumnConfig<OrderProxy, String> legalNumberCol = new ColumnConfig<OrderProxy, String>(props.legalNumber(), 200, "Договор");

		List<ColumnConfig<OrderProxy, ?>> l = new ArrayList<ColumnConfig<OrderProxy, ?>>();
		l.add(codeCol);
		l.add(nameCol);
		l.add(customerCol);
		l.add(legalNumberCol);

		ColumnModel<OrderProxy> model = new ColumnModel<OrderProxy>(l);
		return new OrdersGrid(store, model);
	}

	private OrdersGrid(ListStore<OrderProxy> store, ColumnModel<OrderProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<OrderProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<OrderProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<OrderProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<OrderProxy>> callback) {
				OrderCommon.getService().getOrders(new AsyncCallbackWithErrorMessage<List<OrderProxy>>() {

					@Override
					public void onSuccess(List<OrderProxy> result) {
						PagingLoadResultBean<OrderProxy> r = new PagingLoadResultBean<OrderProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<OrderProxy>>(proxy);
	}
}
