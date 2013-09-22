package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculatorModuleServiceAsync {

	void getResultData(CalculatorInputDataProxy input, AsyncCallback<CalculatorResultDataProxy> callback);

}
