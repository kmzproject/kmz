package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.info.Info;

public class CalculatorModuleView implements EntryPoint {

	private final CalculatorModuleServiceAsync calculatorMpduleService = GWT.create(CalculatorModuleService.class);

	@Override
	public void onModuleLoad() {
		calculatorMpduleService.getResultData(new AsyncCallback<CalculatorResultDataProxy>() {

			@Override
			public void onSuccess(CalculatorResultDataProxy result) {
				CalculatorResultGrid resultGrid = new CalculatorResultGrid();
				resultGrid.setRows(result.getRows());
				RootPanel.get("grid").add(resultGrid);
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "this is error");
			}
		});
	}

}
