package ru.kmz.web.calculator.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.CalculationByFinish;
import ru.kmz.server.engine.calculation.CalculationByStart;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;

@SuppressWarnings("serial")
public class CalculatorModuleServiceImpl extends RemoteServiceServlet implements CalculatorModuleService {

	@Override
	public CalculatorResultDataProxy getResultData(CalculatorInputDataProxy input) {
		CalculatorResultDataProxy resultData = null;
		if (input.getDate() == null) {
			return new CalculatorResultDataProxy();
		}
		List<Template> list = TemplateDataUtils.getAllTemplates();
		Template template = list.get(0);
		if (input.isByFinishDate()) {
			resultData = CalculationByFinish.getCalculationByFinishDate(template, input.getDate());
		} else if (input.isByStartDate()) {
			resultData = CalculationByStart.getCalculationByFinishDate(template, input.getDate());
		}

		return resultData;
	}

}
