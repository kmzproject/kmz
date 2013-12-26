package ru.kmz.web.projects.client.window;

import java.util.List;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.CancelSelectHandler;
import ru.kmz.web.common.client.window.CommonDirectoryWindow;
import ru.kmz.web.ordercommon.client.OrderCommon;
import ru.kmz.web.ordercommon.client.OrderProxyProperties;
import ru.kmz.web.ordercommon.shared.OrderProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.dnd.core.client.ListViewDragSource;
import com.sencha.gxt.dnd.core.client.ListViewDropTarget;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class GanttDataFilterWindow extends CommonDirectoryWindow<GanttDataFilter> {

	private TextButton deleteButton;
	private ListStore<OrderProxy> storeSelected;

	public GanttDataFilterWindow() {
		super();
		setPixelSize(500, 150);
		setHeadingText("Фильтр");
	}

	@Override
	protected Container getInfoContainer() {
		HorizontalLayoutContainer con = new HorizontalLayoutContainer();

		OrderProxyProperties props = GWT.create(OrderProxyProperties.class);
		final ListStore<OrderProxy> storeAll = new ListStore<OrderProxy>(props.key());
		storeAll.addSortInfo(new StoreSortInfo<OrderProxy>(props.name(), SortDir.ASC));
		OrderCommon.getService().getOrders(new AsyncCallbackWithErrorMessage<List<OrderProxy>>() {
			public void onSuccess(List<OrderProxy> result) {
				storeAll.addAll(result);
			};
		});
		ListView<OrderProxy, String> listAllOrders = new ListView<OrderProxy, String>(storeAll, props.name());
		listAllOrders.setWidth(100);

		storeSelected = new ListStore<OrderProxy>(props.key());
		storeSelected.addSortInfo(new StoreSortInfo<OrderProxy>(props.name(), SortDir.ASC));

		ListView<OrderProxy, String> listSelecredOrders = new ListView<OrderProxy, String>(storeSelected, props.name());
		listSelecredOrders.getSelectionModel().setSelectionMode(SelectionMode.MULTI);

		new ListViewDragSource<OrderProxy>(listAllOrders);
		new ListViewDragSource<OrderProxy>(listSelecredOrders);

		new ListViewDropTarget<OrderProxy>(listAllOrders);
		new ListViewDropTarget<OrderProxy>(listSelecredOrders);

		con.add(listAllOrders, new HorizontalLayoutData(.5, 1, new Margins(5)));
		con.add(listSelecredOrders, new HorizontalLayoutData(.5, 1, new Margins(5, 5, 5, 0)));
		return con;
	}

	@Override
	protected void createButtons() {
		super.createButtons();
		deleteButton = new TextButton("Удалить");
		deleteButton.addSelectHandler(new CancelSelectHandler(this) {
			@Override
			public void onSelect(SelectEvent event) {
				GanttDataFilterWindow.this.object = null;
				deleteButton.setEnabled(false);
				super.onSelect(event);
				updatableForm.update();
			}
		});
	}

	@Override
	public void setData(GanttDataFilter object) {

	}

	@Override
	protected void editProcess() {
		if (object == null) {
			object = new GanttDataFilter();
			deleteButton.setEnabled(true);
		}

		for (OrderProxy order : storeSelected.getAll()) {
			object.clearOrders();
			object.addOrderId(order.getId());
		}

		updatableForm.update();
	}

}
