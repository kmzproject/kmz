package ru.kmz.web.common.client;

import ru.kmz.web.common.client.menu.GridComtextMenu;
import ru.kmz.web.common.shared.IHasIdProxy;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public abstract class CommonProxyObjectGrid<T extends IHasIdProxy> extends CommonGrid<T> {

	protected GridComtextMenu<T> contextMenu;

	protected CommonProxyObjectGrid(ListStore<T> store, ColumnModel<T> model) {
		super(store, model);
		contextMenu = new GridComtextMenu<T>(this);
		this.setContextMenu(contextMenu);
	}

	public GridComtextMenu<T> getContextMenu() {
		return contextMenu;
	}
}
