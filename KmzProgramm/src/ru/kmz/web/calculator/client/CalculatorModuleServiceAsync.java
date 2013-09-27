package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.common.shared.gant.GanttData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculatorModuleServiceAsync {

	void getResultData(CalculatorInputDataProxy input, AsyncCallback<CalculatorResultDataProxy> callback);

	void getGantResultData(CalculatorInputDataProxy input, AsyncCallback<GanttData> callback);

}
