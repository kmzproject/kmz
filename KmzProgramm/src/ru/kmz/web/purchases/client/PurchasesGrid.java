package ru.kmz.web.purchases.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonProxyObjectGrid;
import ru.kmz.web.common.client.control.DateCellFormatColor;
import ru.kmz.web.projectscommon.client.PurchaseProxyProperties;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

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

public class PurchasesGrid extends CommonProxyObjectGrid<PurchaseProxy> {

	private static final PurchaseProxyProperties props;
	private ProductElementTaskGridFilter filter;

	static {
		props = GWT.create(PurchaseProxyProperties.class);
	}

	public static PurchasesGrid getCalculatorGrid() {
		ListStore<PurchaseProxy> store = new ListStore<PurchaseProxy>(props.key());

		ColumnConfig<PurchaseProxy, String> codeCol = new ColumnConfig<PurchaseProxy, String>(props.code(), 50, "Код");
		ColumnConfig<PurchaseProxy, String> orderNameCol = new ColumnConfig<PurchaseProxy, String>(props.orderNameAndCode(), 100, "Название заказа");
		ColumnConfig<PurchaseProxy, String> nameCol = new ColumnConfig<PurchaseProxy, String>(props.name(), 200, "Закупка");
		ColumnConfig<PurchaseProxy, String> taskStateCol = new ColumnConfig<PurchaseProxy, String>(props.taskState(), 100, "Состояние");
		ColumnConfig<PurchaseProxy, Date> startCol = new ColumnConfig<PurchaseProxy, Date>(props.planStart(), 200, "Дата начала");
		startCol.setCell(new DateCellFormatColor());
		ColumnConfig<PurchaseProxy, Date> finishCol = new ColumnConfig<PurchaseProxy, Date>(props.planFinish(), 200, "Дата завершения");
		finishCol.setCell(new DateCellFormatColor());

		List<ColumnConfig<PurchaseProxy, ?>> l = new ArrayList<ColumnConfig<PurchaseProxy, ?>>();
		l.add(codeCol);
		l.add(nameCol);
		l.add(taskStateCol);
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

	public void setFilter(ProductElementTaskGridFilter filter) {
		this.filter = filter;
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<PurchaseProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<PurchaseProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<PurchaseProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<PurchaseProxy>> callback) {
				PurchasesModuleView.getService().getActivePurchases(filter, new AsyncCallbackWithErrorMessage<List<PurchaseProxy>>() {
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
