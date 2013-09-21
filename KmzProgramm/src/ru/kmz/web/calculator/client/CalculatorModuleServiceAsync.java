package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculatorModuleServiceAsync {

	void getResultData(AsyncCallback<CalculatorResultDataProxy> callback);

}
