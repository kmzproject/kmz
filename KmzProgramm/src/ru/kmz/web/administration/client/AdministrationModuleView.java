package ru.kmz.web.administration.client;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class AdministrationModuleView extends AbstarctModuleView<VerticalPanel> {

	private final static AdministrationModuleServiceAsync service = GWT.create(AdministrationModuleService.class);

	private static AdministrationModuleView instanse;
	private HorizontalPanel gantContainer;

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
		container = new VerticalPanel();
		container.setSpacing(10);

		createButtons();

		gantContainer = new HorizontalPanel();
		container.add(gantContainer);
	}

	private void createButtons() {
		HorizontalPanel buttonContainer = new HorizontalPanel();
		buttonContainer.setSpacing(10);
		container.add(buttonContainer);

		TextButton select = new TextButton("Удалить все данные и создать новые тестовые");

		select.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				final AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере", "Это может занять некоторое время");
				box.setProgressText("Обработка...");
				box.auto();
				box.show();

				getService().recreateData(new AsyncCallbackWithErrorMessage<Void>() {
					@Override
					public void onSuccess(Void result) {
						Info.display("Администрирование", "Все данные пересозданы");
						box.hide();
					}
				});
			}
		});
		buttonContainer.add(select);
	}

	public static AdministrationModuleServiceAsync getService() {
		return service;
	}

}
