package ru.kmz.web.common.client.menu;

import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.client.window.ShowHistoryWindow;
import ru.kmz.web.common.shared.IHasIdProxy;

public class ShowHistoryMenuItem<T extends IHasIdProxy> extends GridContextMenuItem<T> {

	public ShowHistoryMenuItem(final CommonGrid<T> grid) {
		super(grid, "История");
	}

	@Override
	protected void onSelection(T selectedObject) {
		ShowHistoryWindow window = new ShowHistoryWindow(selectedObject.getId());
		window.show();
	}
}
