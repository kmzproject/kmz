package ru.kmz.web.products.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.control.DateCellFormat;
import ru.kmz.web.projectscommon.client.ProductProxyProperties;
import ru.kmz.web.projectscommon.shared.ProductProxy;

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

public class ProductsGrid extends CommonGrid<ProductProxy> {

	private static final ProductProxyProperties props;

	static {
		props = GWT.create(ProductProxyProperties.class);
	}

	public static ProductsGrid getCalculatorGrid() {
		ListStore<ProductProxy> store = new ListStore<ProductProxy>(props.key());

		ColumnConfig<ProductProxy, String> codeCol = new ColumnConfig<ProductProxy, String>(props.code(), 50, "Код");
		ColumnConfig<ProductProxy, String> orderNameCol = new ColumnConfig<ProductProxy, String>(props.orderName(), 100, "Название заказа");
		ColumnConfig<ProductProxy, String> nameCol = new ColumnConfig<ProductProxy, String>(props.name(), 200, "Закупка");
		ColumnConfig<ProductProxy, Date> startCol = new ColumnConfig<ProductProxy, Date>(props.planStart(), 200, "Дата начала");
		startCol.setCell(new DateCellFormat());
		ColumnConfig<ProductProxy, Date> finishCol = new ColumnConfig<ProductProxy, Date>(props.planFinish(), 200, "Дата завершения");
		finishCol.setCell(new DateCellFormat());

		List<ColumnConfig<ProductProxy, ?>> l = new ArrayList<ColumnConfig<ProductProxy, ?>>();
		l.add(codeCol);
		l.add(nameCol);
		l.add(orderNameCol);
		l.add(startCol);
		l.add(finishCol);

		ColumnModel<ProductProxy> model = new ColumnModel<ProductProxy>(l);
		return new ProductsGrid(store, model);
	}

	private ProductsGrid(ListStore<ProductProxy> store, ColumnModel<ProductProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<ProductProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<ProductProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<ProductProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<ProductProxy>> callback) {
				ProductsModuleView.getService().getActiveProducts(new AsyncCallbackWithErrorMessage<List<ProductProxy>>() {
					@Override
					public void onSuccess(List<ProductProxy> result) {
						PagingLoadResultBean<ProductProxy> r = new PagingLoadResultBean<ProductProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<ProductProxy>>(proxy);
	}
}
