package ru.kmz.web.calculator.server;

import java.util.List;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.gant.GantCalculationService;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;

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
	public GanttData getGantResultData(CalculatorInputDataProxy input) {
		if (!isValid(input)) {
			return new GanttData();
		}
		Template template = getTemplate(input);
		GantCalculationService service = new GantCalculationService();
		if (input.isByFinishDate()) {
			service.calculateFinish(template, input.getDate());
		} else if (input.isByStartDate()) {
			service.calculateStart(template, input.getDate());
		}
		return service.getGantData();
	}

}
