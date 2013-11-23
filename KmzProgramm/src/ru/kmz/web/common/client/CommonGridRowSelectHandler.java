package ru.kmz.web.common.client;

import java.util.List;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public abstract class CommonGridRowSelectHandler<T> implements SelectHandler {

	private CommonGrid<T> grid;

	public CommonGridRowSelectHandler(CommonGrid<T> grid) {
		this.grid = grid;
	}

	@Override
	public void onSelect(SelectEvent event) {
		List<T> list = grid.getSelectionModel().getSelectedItems();
		if (list == null || list.size() != 1) {
			Info.display("Предупреждение", "Невозможно произвести редактирование");
			return;
		}
		onSelect(list.get(0));
	}

	protected abstract void onSelect(T object);

}
