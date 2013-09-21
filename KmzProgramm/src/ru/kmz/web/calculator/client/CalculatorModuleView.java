package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class CalculatorModuleView implements EntryPoint {

	@Override
	public void onModuleLoad() {
		CalculatorResultGrid result = new CalculatorResultGrid();
		CalculatorResultDataProxy data = new CalculatorResultDataProxy();
		data.init();
		result.setRows(data.getRows());
		RootPanel.get("grid").add(result);
	}

}
