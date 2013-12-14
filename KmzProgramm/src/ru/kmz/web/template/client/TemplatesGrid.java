package ru.kmz.web.template.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.templatecommon.client.TemplateCommon;
import ru.kmz.web.templatecommon.client.TemplateTreeDataProxyProperties;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

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

public class TemplatesGrid extends CommonGrid<TemplateTreeDataProxy> {

	private static final TemplateTreeDataProxyProperties props;

	static {
		props = GWT.create(TemplateTreeDataProxyProperties.class);
	}

	public static TemplatesGrid getTemplateGrid() {
		ListStore<TemplateTreeDataProxy> store = new ListStore<TemplateTreeDataProxy>(props.key());

		ColumnConfig<TemplateTreeDataProxy, String> nameCol = new ColumnConfig<TemplateTreeDataProxy, String>(props.name(), 200, "Название шиблона");

		List<ColumnConfig<TemplateTreeDataProxy, ?>> l = new ArrayList<ColumnConfig<TemplateTreeDataProxy, ?>>();
		l.add(nameCol);

		ColumnModel<TemplateTreeDataProxy> model = new ColumnModel<TemplateTreeDataProxy>(l);
		return new TemplatesGrid(store, model);
	}

	private TemplatesGrid(ListStore<TemplateTreeDataProxy> store, ColumnModel<TemplateTreeDataProxy> model) {
		super(store, model);

		setHeight(150);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<TemplateTreeDataProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<TemplateTreeDataProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<TemplateTreeDataProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<TemplateTreeDataProxy>> callback) {
				TemplateCommon.getService().getTemplateList(new AsyncCallbackWithErrorMessage<List<TemplateTreeDataProxy>>() {

					@Override
					public void onSuccess(List<TemplateTreeDataProxy> result) {
						PagingLoadResultBean<TemplateTreeDataProxy> r = new PagingLoadResultBean<TemplateTreeDataProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<TemplateTreeDataProxy>>(proxy);
	}
}
