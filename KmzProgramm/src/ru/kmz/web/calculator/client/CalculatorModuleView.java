package ru.kmz.web.calculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class CalculatorModuleView implements EntryPoint {

	@Override
	public void onModuleLoad() {
		CalculatorResult result = new CalculatorResult();
		RootPanel.get("calculator").add(result);
	}

}
