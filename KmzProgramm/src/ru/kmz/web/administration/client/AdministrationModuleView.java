package ru.kmz.web.administration.client;

import ru.kmz.web.administration.client.window.SelectNewPasswordWindow;
import ru.kmz.web.administration.client.window.UserPropertiesWindow;
import ru.kmz.web.administration.shared.UserProxy;
import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.CommonGridRowSelectHandler;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.common.client.window.IUpdatableWithValue;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class AdministrationModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private final static AdministrationModuleServiceAsync service = GWT.create(AdministrationModuleService.class);

	private static AdministrationModuleView instanse;
	private UsersGrid grid;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Администрирование";
	}

	public static AdministrationModuleView getInstance() {
		if (instanse == null)
			instanse = new AdministrationModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		grid = UsersGrid.getCalculatorGrid();
		container.add(createToolBar());

		container.add(grid);
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});

		TextButton setNewPasswordButton = new TextButton("Новый пароль");
		setNewPasswordButton.addSelectHandler(new CommonGridRowSelectHandler<UserProxy>(grid) {

			@Override
			protected void onSelect(UserProxy selectedObject) {
				final String userName = selectedObject.getUsername();
				SelectNewPasswordWindow window = new SelectNewPasswordWindow();
				window.setUpdatable(new IUpdatableWithValue<String>() {

					@Override
					public void update(String value) {
						getService().setNewPasswork(userName, value, new AsyncCallbackWithErrorMessage<Void>() {
							@Override
							public void onSuccess(Void result) {
								Info.display(userName, "Пароль успешно изменен");
							}
						});
					}
				});
				window.show();

			}
		});

		TextButton setUserButton = new TextButton("Новый пользователь");
		setUserButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				UserPropertiesWindow window = new UserPropertiesWindow();
				window.setUpdatable(AdministrationModuleView.this);
				window.show();
			}
		});

		toolBar.add(setUserButton);
		toolBar.add(setNewPasswordButton);
		toolBar.add(refreshButton);

		return toolBar;
	}

	public static AdministrationModuleServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
