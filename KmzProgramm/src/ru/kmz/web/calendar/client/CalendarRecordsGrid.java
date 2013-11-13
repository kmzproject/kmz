package ru.kmz.web.calendar.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.calendar.shared.CalendarRecordProxy;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.control.DateCellFormatt;

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

public class CalendarRecordsGrid extends CommonGrid<CalendarRecordProxy> {

	private static final CalendarRecordProxyProperties props;

	static {
		props = GWT.create(CalendarRecordProxyProperties.class);
	}

	public static CalendarRecordsGrid getCalculatorGrid() {
		ListStore<CalendarRecordProxy> store = new ListStore<CalendarRecordProxy>(props.key());

		ColumnConfig<CalendarRecordProxy, Date> dateCol = new ColumnConfig<CalendarRecordProxy, Date>(props.date(), 50, "Дата");
		dateCol.setCell(new DateCellFormatt());
		ColumnConfig<CalendarRecordProxy, String> commentCol = new ColumnConfig<CalendarRecordProxy, String>(props.comment(), 200, "Примечание");

		List<ColumnConfig<CalendarRecordProxy, ?>> l = new ArrayList<ColumnConfig<CalendarRecordProxy, ?>>();
		l.add(dateCol);
		l.add(commentCol);

		ColumnModel<CalendarRecordProxy> model = new ColumnModel<CalendarRecordProxy>(l);
		return new CalendarRecordsGrid(store, model);
	}

	private CalendarRecordsGrid(ListStore<CalendarRecordProxy> store, ColumnModel<CalendarRecordProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<CalendarRecordProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<CalendarRecordProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<CalendarRecordProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<CalendarRecordProxy>> callback) {
				CalendarModuleView.getService().getCalendarRecords(new AsyncCallbackWithErrorMessage<List<CalendarRecordProxy>>() {

					@Override
					public void onSuccess(List<CalendarRecordProxy> result) {
						PagingLoadResultBean<CalendarRecordProxy> r = new PagingLoadResultBean<CalendarRecordProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<CalendarRecordProxy>>(proxy);
	}
}
