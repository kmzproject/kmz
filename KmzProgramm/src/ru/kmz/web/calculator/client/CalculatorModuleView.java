package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.client.CalculatorInputData.CalculateHandler;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.ganttcommon.client.ProjectsGantt;
import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.info.Info;

public class CalculatorModuleView implements EntryPoint, IsWidget, IKmzModule, CalculateHandler {

	private final static CalculatorModuleServiceAsync calculatorModuleService = GWT
			.create(CalculatorModuleService.class);
	private static CalculatorModuleView instanse;

	private CalculatorInputData input;
	private HorizontalPanel gantContainer;
	private ProjectsGantt gantt;

	@Override
	public void onModuleLoad() {
		instanse = this;
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Модуль расчетов + гант";
	}

	@Override
	public Widget asWidget() {
		CellPanel container = new VerticalPanel();
		container.setSpacing(10);

		input = new CalculatorInputData(this);
		gantContainer = new HorizontalPanel();

		container.add(input);
		container.add(gantContainer);

		return container;
	}

	public static CalculatorModuleView getInstance() {
		if (instanse == null)
			instanse = new CalculatorModuleView();
		return instanse;
	}

	public static CalculatorModuleServiceAsync getService() {
		return calculatorModuleService;
	}

	@Override
	public void onCalculate(CalculatorInputDataProxy data) {
		final AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере",
				"Это может занять некоторое время");
		box.setProgressText("Обработка...");
		box.auto();
		box.show();

		getService().getGantResultData(data, new AsyncCallback<GanttData>() {

			@Override
			public void onSuccess(GanttData result) {
				if (result.getError() != null) {
					Info.display("Error", "Ошибка при обработке " + result.getError());
				} else {
					if (gantt == null){
						gantt = new ProjectsGantt(result);
						gantContainer.clear();
						gantContainer.add(gantt);
					}else{
						gantt.refreshData(result);
					}
				}
				box.hide();
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "This is error " + caught);
			}
		});
	}
}
