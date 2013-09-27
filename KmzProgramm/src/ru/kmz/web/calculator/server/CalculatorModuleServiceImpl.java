package ru.kmz.web.calculator.server;

import java.util.List;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.CalculationByFinish;
import ru.kmz.server.engine.calculation.CalculationByStart;
import ru.kmz.server.engine.gant.GantCalculationService;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.gant.shared.gant.GanttData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CalculatorModuleServiceImpl extends RemoteServiceServlet implements CalculatorModuleService {

	private Template getTemplate(CalculatorInputDataProxy input) {
		List<Template> list = TemplateDataUtils.getAllTemplates();
		return list.get(0);
	}

	private boolean isValid(CalculatorInputDataProxy input) {
		return input.getDate() != null;
	}

	@Override
	public CalculatorResultDataProxy getResultData(CalculatorInputDataProxy input) {
		if (isValid(input)) {
			return new CalculatorResultDataProxy();
		}
		Template template = getTemplate(input);
		CalculatorResultDataProxy resultData = null;
		if (input.isByFinishDate()) {
			resultData = CalculationByFinish.getCalculationByFinishDate(template, input.getDate());
		} else if (input.isByStartDate()) {
			resultData = CalculationByStart.getCalculationByFinishDate(template, input.getDate());
		}

		return resultData;
	}

	@Override
	public GanttData getGantResultData(CalculatorInputDataProxy input) {
		if (!isValid(input)) {
			return new GanttData();
		}
		Template template = getTemplate(input);
		if (input.isByFinishDate()) {
			GantCalculationService service = new GantCalculationService();
			service.calculate(template, input.getDate());
			return service.getGantData();
		} else if (input.isByStartDate()) {
		}
		return null;
	}

}
