package ru.kmz.web.calculator.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.kmz.server.engine.calculation.CalculationUtils;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

@SuppressWarnings("serial")
public class CalculatorModuleServiceImpl extends RemoteServiceServlet implements CalculatorModuleService {

	@Override
	public CalculatorResultDataProxy getResultData(CalculatorInputDataProxy input) {
		if (input.getFinishDate() == null)
			return new CalculatorResultDataProxy();
		return CalculationUtils.getCalculationByFinishDate(input.getFinishDate());
	}

}
