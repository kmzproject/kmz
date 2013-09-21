package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.common.client.IKmzModule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;

public class CalculatorModuleView implements EntryPoint, IsWidget, IKmzModule {

	private final CalculatorModuleServiceAsync calculatorMpduleService = GWT.create(CalculatorModuleService.class);
	private static CalculatorModuleView instanse;

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
		final Container caintainer = new HorizontalLayoutContainer();
		calculatorMpduleService.getResultData(new AsyncCallback<CalculatorResultDataProxy>() {

			@Override
			public void onSuccess(CalculatorResultDataProxy result) {
				CalculatorResultGrid resultGrid = new CalculatorResultGrid();
				resultGrid.setRows(result.getRows());
				caintainer.add(resultGrid);
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "this is error " + caught);
			}
		});
		return caintainer;
	}

	public static CalculatorModuleView getInstance() {
		if (instanse == null)
			instanse = new CalculatorModuleView();
		return instanse;
	}

}
