package ru.kmz.web.common.client.window;

import ru.kmz.web.common.client.control.HistoryGrid;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class ShowHistoryWindow extends Window {

	private long objectId;

	public ShowHistoryWindow(long objectId) {
		super();

		this.objectId = objectId;

		add(getInfoContainer());

		setPixelSize(500, 300);
		setModal(true);
		setBlinkModal(true);
		createButtons();
	}

	private Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();
		HistoryGrid grid = HistoryGrid.getHistoryGrid();
		grid.setObjectId(objectId);
		p.add(grid);
		return p;
	}

	protected void createButtons() {
		TextButton cancelButton = new TextButton("Закрыть");
		cancelButton.addSelectHandler(new CancelSelectHandler(this));
		addButton(cancelButton);
	}

}