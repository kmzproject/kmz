package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calculatorModule")
public interface CalculatorModuleService extends RemoteService {

	GanttData getGantResultData(CalculatorInputDataProxy input);

	void save(CalculatorInputDataProxy input, String orderId);
}
