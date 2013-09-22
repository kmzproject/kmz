package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.common.client.IKmzModule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.info.Info;

public class CalculatorModuleView implements EntryPoint, IsWidget, IKmzModule {

	private final static CalculatorModuleServiceAsync calculatorModuleService = GWT
			.create(CalculatorModuleService.class);
	private static CalculatorModuleView instanse;

	private CalculatorInputData input;
	private CalculatorResultGrid resultGrid;

	@Override
	public void onModuleLoad() {
		if (RootPanel.get("grid") != null)
			RootPanel.get("grid").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Модуль расчетов";
	}

	@Override
	public Widget asWidget() {
		HorizontalPanel container = new HorizontalPanel();
		container.setSpacing(10);

		input = new CalculatorInputData(this);
		resultGrid = CalculatorResultGrid.getCalculatorGrid();

		container.add(input);
		container.add(resultGrid);

		return container;
	}

	public void showResult(CalculatorInputDataProxy input) {
		getService().getResultData(input, new AsyncCallback<CalculatorResultDataProxy>() {

			@Override
			public void onSuccess(CalculatorResultDataProxy result) {
				resultGrid.setRows(result.getRows());
				resultGrid.updateData();
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "This is error " + caught);
			}
		});
	}

	public static CalculatorModuleView getInstance() {
		if (instanse == null)
			instanse = new CalculatorModuleView();
		return instanse;
	}

	public static CalculatorModuleServiceAsync getService() {
		return calculatorModuleService;
	}
}
