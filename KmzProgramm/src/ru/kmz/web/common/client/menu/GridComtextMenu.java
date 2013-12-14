package ru.kmz.web.common.client.menu;

import ru.kmz.web.common.client.CommonGrid;
import ru.kmz.web.common.shared.IHasIdProxy;

import com.sencha.gxt.widget.core.client.menu.Menu;

public class GridComtextMenu<T extends IHasIdProxy> extends Menu {

	public GridComtextMenu(CommonGrid<T> grid) {
		super();
		add(new ShowHistoryMenuItem<T>(grid));
	}
}
