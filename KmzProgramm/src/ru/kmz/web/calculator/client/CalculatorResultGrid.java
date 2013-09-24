package ru.kmz.web.calculator.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.calculator.shared.CalculatorResultRowProxy;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.DateUtils;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class CalculatorResultGrid extends CommonGrid<CalculatorResultRowProxy> {

	private static final CalculatorResultRowProxyProperties props;

	private List<CalculatorResultRowProxy> rows;

	static {
		props = GWT.create(CalculatorResultRowProxyProperties.class);
	}

	public static CalculatorResultGrid getCalculatorGrid() {
		ListStore<CalculatorResultRowProxy> store = new ListStore<CalculatorResultRowProxy>(props.key());

		ColumnConfig<CalculatorResultRowProxy, String> nameCol = new ColumnConfig<CalculatorResultRowProxy, String>(
				props.name(), 100, "Название");
		ColumnConfig<CalculatorResultRowProxy, String> resourceTypeCol = new ColumnConfig<CalculatorResultRowProxy, String>(
				props.resourceType(), 100, "Тип ресурса");
		ColumnConfig<CalculatorResultRowProxy, Integer> durationCol = new ColumnConfig<CalculatorResultRowProxy, Integer>(
				props.duration(), 50, "Дней");
		ColumnConfig<CalculatorResultRowProxy, Date> startDate = new ColumnConfig<CalculatorResultRowProxy, Date>(
				props.startDate(), 100, "Начало");
		ColumnConfig<CalculatorResultRowProxy, Date> finishDate = new ColumnConfig<CalculatorResultRowProxy, Date>(
				props.finishDate(), 100, "Окончание");

		startDate.setCell(new DateCell(DateTimeFormat.getFormat(DateUtils.PATTERN)));
		finishDate.setCell(new DateCell(DateTimeFormat.getFormat(DateUtils.PATTERN)));

		List<ColumnConfig<CalculatorResultRowProxy, ?>> l = new ArrayList<ColumnConfig<CalculatorResultRowProxy, ?>>();
		l.add(nameCol);
		l.add(resourceTypeCol);
		l.add(durationCol);
		l.add(startDate);
		l.add(finishDate);

		ColumnModel<CalculatorResultRowProxy> model = new ColumnModel<CalculatorResultRowProxy>(l);
		return new CalculatorResultGrid(store, model);
	}

	private CalculatorResultGrid(ListStore<CalculatorResultRowProxy> store, ColumnModel<CalculatorResultRowProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(false);
	}

	public void setRows(List<CalculatorResultRowProxy> rows) {
		this.rows = rows;
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<CalculatorResultRowProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<CalculatorResultRowProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<CalculatorResultRowProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					final AsyncCallback<PagingLoadResult<CalculatorResultRowProxy>> callback) {
				PagingLoadResultBean<CalculatorResultRowProxy> r = new PagingLoadResultBean<CalculatorResultRowProxy>();
				r.setData(rows);
				callback.onSuccess(r);
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<CalculatorResultRowProxy>>(proxy);
	}
}
