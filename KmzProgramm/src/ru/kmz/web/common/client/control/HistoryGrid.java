package ru.kmz.web.common.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.CommonModule;
import ru.kmz.web.common.shared.HistoryProxy;
import ru.kmz.web.common.shared.HistoryProxyProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class HistoryGrid extends CommonGrid<HistoryProxy> {

	private static final HistoryProxyProperties props;

	static {
		props = GWT.create(HistoryProxyProperties.class);
	}

	private Long objectId;

	public static HistoryGrid getHistoryGrid() {
		ListStore<HistoryProxy> store = new ListStore<HistoryProxy>(props.key());

		ColumnConfig<HistoryProxy, Date> dateCol = new ColumnConfig<HistoryProxy, Date>(props.date(), 50, "Время");
		dateCol.setCell(new DateAndTimeCellFormat());

		ColumnConfig<HistoryProxy, String> nameCol = new ColumnConfig<HistoryProxy, String>(props.name(), 50, "Тип действия");
		ColumnConfig<HistoryProxy, String> commentCol = new ColumnConfig<HistoryProxy, String>(props.comment(), 200, "Описание");
		ColumnConfig<HistoryProxy, String> userCol = new ColumnConfig<HistoryProxy, String>(props.user(), 50, "Пользователь");

		List<ColumnConfig<HistoryProxy, ?>> l = new ArrayList<ColumnConfig<HistoryProxy, ?>>();
		l.add(userCol);
		l.add(dateCol);
		l.add(nameCol);
		l.add(commentCol);

		ColumnModel<HistoryProxy> model = new ColumnModel<HistoryProxy>(l);
		return new HistoryGrid(store, model);
	}

	protected HistoryGrid(ListStore<HistoryProxy> store, ColumnModel<HistoryProxy> model) {
		super(store, model);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<HistoryProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<HistoryProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<HistoryProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<HistoryProxy>> callback) {
				CommonModule.getService().getHistoryByObject(objectId, new AsyncCallbackWithErrorMessage<List<HistoryProxy>>() {

					@Override
					public void onSuccess(List<HistoryProxy> result) {
						PagingLoadResultBean<HistoryProxy> r = new PagingLoadResultBean<HistoryProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<HistoryProxy>>(proxy);
	}

	@Override
	protected void inOnAfterFirstAttach() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				loader.load();
				Timer timer = new Timer() {
					public void run() {
						loader.load();
					}
				};
				timer.scheduleRepeating(1000 * 30);
			}
		});
	}

}
