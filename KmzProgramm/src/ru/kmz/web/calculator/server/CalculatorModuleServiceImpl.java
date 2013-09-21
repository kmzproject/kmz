package ru.kmz.web.calculator.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

@SuppressWarnings("serial")
public class CalculatorModuleServiceImpl extends RemoteServiceServlet implements CalculatorModuleService {

	@Override
	public CalculatorResultDataProxy getResultData() {
		return CalculatorResultDataExample.getExample();
	}

}
