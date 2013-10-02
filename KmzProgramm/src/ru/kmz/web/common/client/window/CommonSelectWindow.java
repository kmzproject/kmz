package ru.kmz.web.common.client.window;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public abstract class CommonSelectWindow<T> extends Window {

	protected IUpdatableWithValue<T> updatableForm;

	public CommonSelectWindow() {
		super();

		add(getInfoContainer());

		setPixelSize(500, 100);
		setModal(true);
		setBlinkModal(true);
		TextButton cancelButton = new TextButton("Отмена");
		cancelButton.addSelectHandler(new CancelSelectHandler(this));
		TextButton saveButton = new TextButton("Выбрать");
		saveButton.addSelectHandler(new SaveSelectHandler());
		addButton(saveButton);
		addButton(cancelButton);
	}

	protected abstract Container getInfoContainer();

	public void setUpdatable(IUpdatableWithValue<T> form) {
		this.updatableForm = form;
	}

	protected abstract T getSelectedValue();

	private class SaveSelectHandler extends CancelSelectHandler {

		public SaveSelectHandler() {
			super(CommonSelectWindow.this);
		}

		@Override
		public void onSelect(SelectEvent event) {
			updatableForm.update(getSelectedValue());
			super.onSelect(event);
		}
	}

}