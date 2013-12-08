package ru.kmz.web.administration.client.window;

import ru.kmz.web.administration.client.AdministrationModuleView;
import ru.kmz.web.administration.shared.UserProxy;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.CommonDirectoryWindow;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class UserPropertiesWindow extends CommonDirectoryWindow<UserProxy> {

	private TextField userName;

	public UserPropertiesWindow() {
		super();
		setPixelSize(500, 100);
		setHeadingText("Пользователь");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		userName = new TextField();
		userName.setAllowBlank(false);
		userName.setEmptyText("Введите имя...");
		p.add(new FieldLabel(userName, "Имя"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(UserProxy object) {
		this.object = object;
		userName.setValue(object.getUsername());
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new UserProxy();
		object.setUsername(userName.getValue());
		AdministrationModuleView.getService().editUser(object, new AsyncCallbackWithErrorMessage<UserProxy>() {
			@Override
			public void onSuccess(UserProxy result) {
				Info.display("Данные сохранены", result.getUsername());
				if (updatableForm != null)
					updatableForm.update();
			}
		});
	}

}
