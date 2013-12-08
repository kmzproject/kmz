package ru.kmz.web.administration.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.administration.shared.UserProxy;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;

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

public class UsersGrid extends CommonGrid<UserProxy> {

	private static final UserProxyProperties props;

	static {
		props = GWT.create(UserProxyProperties.class);
	}

	public static UsersGrid getCalculatorGrid() {
		ListStore<UserProxy> store = new ListStore<UserProxy>(props.id());

		ColumnConfig<UserProxy, String> nameCol = new ColumnConfig<UserProxy, String>(props.username(), 200, "Пользователь");

		List<ColumnConfig<UserProxy, ?>> l = new ArrayList<ColumnConfig<UserProxy, ?>>();
		l.add(nameCol);

		ColumnModel<UserProxy> model = new ColumnModel<UserProxy>(l);
		return new UsersGrid(store, model);
	}

	private UsersGrid(ListStore<UserProxy> store, ColumnModel<UserProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<UserProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<UserProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<UserProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<UserProxy>> callback) {
				AdministrationModuleView.getService().getUsers(new AsyncCallbackWithErrorMessage<List<UserProxy>>() {
					@Override
					public void onSuccess(List<UserProxy> result) {
						PagingLoadResultBean<UserProxy> r = new PagingLoadResultBean<UserProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<UserProxy>>(proxy);
	}
}
