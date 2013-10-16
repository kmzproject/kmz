package ru.kmz.web.resources.client;

import java.util.List;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.resources.client.window.ResourceProperties;
import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class ResourcesModuleView extends AbstarctModuleView<VerticalPanel> implements IUpdatable {

	private static ResourcesModuleView instance;
	private ResourcesGrid grid;
	private final static ResourcesModuleServiceAsync resourcesModuleService = GWT.create(ResourcesModuleService.class);

	@Override
	public boolean getEnabled() {
		return false;
	}

	@Override
	public void onModuleLoad() {
		instance = this;
		if (RootPanel.get("resources") != null)
			RootPanel.get("resources").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Ресурсы";
	}

	@Override
	protected void createContainer() {
		container = new VerticalPanel();
		container.setSpacing(10);

		HorizontalPanel hp = new HorizontalPanel();

		TextButton addButton = new TextButton("Добавить");
		addButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				ResourceProperties window = new ResourceProperties();
				window.setUpdatable(ResourcesModuleView.this);
				window.show();
			}
		});
		TextButton editButton = new TextButton("Редактировать");
		editButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				List<ResourceProxy> list = grid.getSelectionModel().getSelectedItems();
				if (list == null || list.size() != 1) {
					Info.display("Предупреждение", "Невозможно произвести редактирование");
					return;
				}
				ResourceProxy object = list.get(0);
				ResourceProperties window = new ResourceProperties();
				window.setUpdatable(ResourcesModuleView.this);
				window.setData(object);
				window.show();
			}
		});
		hp.add(addButton);
		hp.add(editButton);

		container.add(hp);

		grid = ResourcesGrid.getCalculatorGrid();
		container.add(grid);
	}

	public static ResourcesModuleView getInstance() {
		if (instance == null)
			instance = new ResourcesModuleView();
		return instance;
	}

	public static ResourcesModuleServiceAsync getService() {
		return resourcesModuleService;
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
