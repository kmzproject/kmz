package ru.kmz.web.common.client;

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
		T selectedObject = grid.getSelectionModel().getSelectedItem();
		if (selectedObject == null) {
			Info.display("Предупреждение", "Необходимо выбрать запись");
			return;
		}
		onSelect(selectedObject);
	}

	protected abstract void onSelect(T object);

}
