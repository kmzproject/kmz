package ru.kmz.web.purchases.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.purchases.shared.PurchaseProxy;

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

public class PurchasesGrid extends CommonGrid<PurchaseProxy> {

	private static final PurchaseProxyProperties props;

	static {
		props = GWT.create(PurchaseProxyProperties.class);
	}

	public static PurchasesGrid getCalculatorGrid() {
		ListStore<PurchaseProxy> store = new ListStore<PurchaseProxy>(props.key());

		ColumnConfig<PurchaseProxy, String> orderNameCol = new ColumnConfig<PurchaseProxy, String>(props.orderName(), 300, "Название заказа");
		ColumnConfig<PurchaseProxy, String> nameCol = new ColumnConfig<PurchaseProxy, String>(props.name(), 200, "Закупка");
		ColumnConfig<PurchaseProxy, Date> startCol = new ColumnConfig<PurchaseProxy, Date>(props.planStart(), 200, "Дата начала");
		ColumnConfig<PurchaseProxy, Date> finishCol = new ColumnConfig<PurchaseProxy, Date>(props.planFinish(), 200, "Дата завершения");

		List<ColumnConfig<PurchaseProxy, ?>> l = new ArrayList<ColumnConfig<PurchaseProxy, ?>>();
		l.add(nameCol);
		l.add(orderNameCol);
		l.add(startCol);
		l.add(finishCol);

		ColumnModel<PurchaseProxy> model = new ColumnModel<PurchaseProxy>(l);
		return new PurchasesGrid(store, model);
	}

	private PurchasesGrid(ListStore<PurchaseProxy> store, ColumnModel<PurchaseProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<PurchaseProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<PurchaseProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<PurchaseProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<PurchaseProxy>> callback) {
				PurchasesModuleView.getService().getActivePurchases(new AsyncCallbackWithErrorMessage<List<PurchaseProxy>>() {
					@Override
					public void onSuccess(List<PurchaseProxy> result) {
						PagingLoadResultBean<PurchaseProxy> r = new PagingLoadResultBean<PurchaseProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<PurchaseProxy>>(proxy);
	}
}
