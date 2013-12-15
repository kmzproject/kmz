package ru.kmz.web.common.client.menu;

import ru.kmz.web.common.client.CommonGrid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public abstract class GridContextMenuItem<T> extends MenuItem {

	public GridContextMenuItem(final CommonGrid<T> grid, String lable) {
		super(lable);
		addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				T selectedObject = grid.getSelectionModel().getSelectedItem();
				if (selectedObject == null) {
					Info.display("", "");
					return;
				}
				GridContextMenuItem.this.onSelection(selectedObject);
			}
		});
	}

	protected abstract void onSelection(T selectedObject);
}
