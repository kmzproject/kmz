package ru.kmz.web.projectschart.client;

import java.util.List;

import ru.kmz.web.common.client.AbstarctModuleView;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.IUpdatable;
import ru.kmz.web.projectschart.shared.FunctioningCapacityParams;
import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;
import ru.kmz.web.templatecommon.client.control.ResourceComboBoxUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProjectsChartModuleView extends AbstarctModuleView<VerticalLayoutContainer> implements IUpdatable {

	private static ProjectsChartModuleView instanse;
	private static ProjectsChartServiceAsync service = GWT.create(ProjectsChartService.class);

	private FunctioningCapacityChart chart;
	private DateField dateFrom, dateTo;
	private ComboBox<KeyValueData> resourceComboBox;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "График загрузки";
	}

	public static ProjectsChartModuleView getInstance() {
		if (instanse == null)
			instanse = new ProjectsChartModuleView();
		return instanse;
	}

	@Override
	protected void createContainer() {
		container = new VerticalLayoutContainer();

		container.add(createToolBar());

		chart = new FunctioningCapacityChart();
		container.add(chart);

	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		dateFrom = new DateField();
		toolBar.add(new FieldLabel(dateFrom, "Начиная с"));

		dateTo = new DateField();
		toolBar.add(new FieldLabel(dateTo, "по"));

		resourceComboBox = ResourceComboBoxUtils.createResourceComboBox();
		toolBar.add(resourceComboBox);

		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});
		toolBar.add(refreshButton);

		return toolBar;
	}

	public static ProjectsChartServiceAsync getService() {
		return service;
	}

	@Override
	public void update() {
		if (dateFrom.getValue() == null || dateTo.getValue() == null) {
			Info.display("Ошибка", "Не верный формат даты");
			return;
		}
		FunctioningCapacityParams params = new FunctioningCapacityParams();
		params.setFrom(dateFrom.getValue());
		params.setTo(dateTo.getValue());
		params.setResourceType(resourceComboBox.getValue().getValue());

		getService().getFunctioningCapacity(params, new AsyncCallbackWithErrorMessage<List<FunctioningCapacityProxy>>() {

			@Override
			public void onSuccess(List<FunctioningCapacityProxy> result) {
				chart.setData(result);
			}

		});
	}
}
