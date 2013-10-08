package ru.kmz.web.calculator.client;

import java.util.List;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.OrderProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculatorModuleServiceAsync {

	void getGantResultData(CalculatorInputDataProxy input, AsyncCallback<GanttData> callback);

	void getOrders(AsyncCallback<List<OrderProxy>> callback);

	void save(CalculatorInputDataProxy input, String orderId, AsyncCallback<Void> callback);

}
