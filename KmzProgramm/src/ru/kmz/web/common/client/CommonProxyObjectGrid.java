package ru.kmz.web.common.client;

import ru.kmz.web.common.client.menu.GridComtextMenu;
import ru.kmz.web.common.shared.IHasIdProxy;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public abstract class CommonProxyObjectGrid<T extends IHasIdProxy> extends CommonGrid<T> {

	protected CommonProxyObjectGrid(ListStore<T> store, ColumnModel<T> model) {
		super(store, model);

		this.setContextMenu(new GridComtextMenu<T>(this));
	}
}
