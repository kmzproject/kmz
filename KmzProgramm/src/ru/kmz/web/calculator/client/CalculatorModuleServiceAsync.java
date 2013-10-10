package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculatorModuleServiceAsync {

	void getGantResultData(CalculatorInputDataProxy input, AsyncCallback<GanttData> callback);

	void save(CalculatorInputDataProxy input, String orderId, AsyncCallback<Void> callback);

}
