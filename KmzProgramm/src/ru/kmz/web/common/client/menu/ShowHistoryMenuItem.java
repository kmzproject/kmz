package ru.kmz.web.common.client.menu;

import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.window.ShowHistoryWindow;
import ru.kmz.web.common.shared.IHasIdProxy;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ShowHistoryMenuItem<T extends IHasIdProxy> extends MenuItem {

	public ShowHistoryMenuItem(final CommonGrid<T> grid) {
		super("История");
		addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				T selectedObject = grid.getSelectionModel().getSelectedItem();
				if (selectedObject == null) {
					// TODO: надо выдавать уведомление
					return;
				}
				ShowHistoryWindow window = new ShowHistoryWindow(selectedObject.getId());
				window.show();
			}
		});

	}
}
