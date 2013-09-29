package ru.kmz.web.resources.client;

import java.util.ArrayList;
import java.util.List;

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
import com.sencha.gxt.widget.core.client.info.Info;

import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.resources.shared.ResourceProxy;

public class ResourcesGrid extends CommonGrid<ResourceProxy> {

	private static final ResourceProxyProperties props;

	static {
		props = GWT.create(ResourceProxyProperties.class);
	}

	public static ResourcesGrid getCalculatorGrid() {
		ListStore<ResourceProxy> store = new ListStore<ResourceProxy>(props.key());

		ColumnConfig<ResourceProxy, String> nameCol = new ColumnConfig<ResourceProxy, String>(props.name(), 300,
				"Название");
		ColumnConfig<ResourceProxy, String> resourceTypeCol = new ColumnConfig<ResourceProxy, String>(
				props.resourceType(), 200, "Тип ресурса");

		List<ColumnConfig<ResourceProxy, ?>> l = new ArrayList<ColumnConfig<ResourceProxy, ?>>();
		l.add(nameCol);
		l.add(resourceTypeCol);

		ColumnModel<ResourceProxy> model = new ColumnModel<ResourceProxy>(l);
		return new ResourcesGrid(store, model);
	}

	private ResourcesGrid(ListStore<ResourceProxy> store, ColumnModel<ResourceProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<ResourceProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<ResourceProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<ResourceProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<ResourceProxy>> callback) {
				ResourcesModuleView.getService().getAllResources(new AsyncCallback<List<ResourceProxy>>() {
					@Override
					public void onSuccess(List<ResourceProxy> result) {
						PagingLoadResultBean<ResourceProxy> r = new PagingLoadResultBean<ResourceProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", "Ошибка " + caught);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<ResourceProxy>>(proxy);
	}
}
