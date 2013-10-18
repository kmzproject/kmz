package ru.kmz.web.common.client.window;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public abstract class CommonSelectWindow<T> extends Window {

	protected IUpdatableWithValue<T> updatableForm;
	final protected TextButton cancelButton;
	final protected TextButton selectButton;

	public CommonSelectWindow() {
		super();

		add(getInfoContainer());

		setPixelSize(500, 100);
		setModal(true);
		setBlinkModal(true);
		cancelButton = new TextButton("Отмена");
		cancelButton.addSelectHandler(new CancelSelectHandler(this));
		selectButton = new TextButton("Выбрать");
		selectButton.addSelectHandler(new SaveSelectHandler());
		addButton(selectButton);
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