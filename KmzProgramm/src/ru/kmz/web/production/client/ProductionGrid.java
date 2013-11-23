package ru.kmz.web.production.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.control.DateCellFormatColor;
import ru.kmz.web.projectscommon.client.ProductionProxyProperties;
import ru.kmz.web.projectscommon.shared.ProductionProxy;

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

public class ProductionGrid extends CommonGrid<ProductionProxy> {

	private static final ProductionProxyProperties props;

	static {
		props = GWT.create(ProductionProxyProperties.class);
	}

	public static ProductionGrid getCalculatorGrid() {
		ListStore<ProductionProxy> store = new ListStore<ProductionProxy>(props.key());

		ColumnConfig<ProductionProxy, String> codeCol = new ColumnConfig<ProductionProxy, String>(props.code(), 50, "Код");
		ColumnConfig<ProductionProxy, String> orderNameCol = new ColumnConfig<ProductionProxy, String>(props.orderName(), 100, "Название заказа");
		ColumnConfig<ProductionProxy, String> nameCol = new ColumnConfig<ProductionProxy, String>(props.name(), 200, "Закупка");
		ColumnConfig<ProductionProxy, String> taskStateCol = new ColumnConfig<ProductionProxy, String>(props.taskState(), 100, "Состояние");
		ColumnConfig<ProductionProxy, Date> startCol = new ColumnConfig<ProductionProxy, Date>(props.planStart(), 200, "Дата начала");
		startCol.setCell(new DateCellFormatColor());
		ColumnConfig<ProductionProxy, Date> finishCol = new ColumnConfig<ProductionProxy, Date>(props.planFinish(), 200, "Дата завершения");
		finishCol.setCell(new DateCellFormatColor());

		List<ColumnConfig<ProductionProxy, ?>> l = new ArrayList<ColumnConfig<ProductionProxy, ?>>();
		l.add(codeCol);
		l.add(nameCol);
		l.add(taskStateCol);
		l.add(orderNameCol);
		l.add(startCol);
		l.add(finishCol);

		ColumnModel<ProductionProxy> model = new ColumnModel<ProductionProxy>(l);
		return new ProductionGrid(store, model);
	}

	private ProductionGrid(ListStore<ProductionProxy> store, ColumnModel<ProductionProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<ProductionProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<ProductionProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<ProductionProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<ProductionProxy>> callback) {
				ProductionModuleView.getService().getActiveProductions(new AsyncCallbackWithErrorMessage<List<ProductionProxy>>() {
					@Override
					public void onSuccess(List<ProductionProxy> result) {
						PagingLoadResultBean<ProductionProxy> r = new PagingLoadResultBean<ProductionProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<ProductionProxy>>(proxy);
	}
}
