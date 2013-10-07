package ru.kmz.web.calculator.server;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.ResourcesDataUtils;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.gant.GantCalculationService;
import ru.kmz.server.engine.calculation.resources.CalculateExecutaionFirstFreeService;
import ru.kmz.server.engine.calculation.resources.CalculateExecutionNoResourceService;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CalculatorModuleServiceImpl extends RemoteServiceServlet implements CalculatorModuleService {

	private boolean isValid(CalculatorInputDataProxy input) {
		return input.getDate() != null;
	}

	@Override
	public GanttData getGantResultData(CalculatorInputDataProxy input) {
		if (!isValid(input)) {
			return GanttData.getError("Не верный формат даты");
		}
		Template template = TemplateDataUtils.getTemplate(input.getTemplateId());

		GantCalculationService service = new GantCalculationService();
		if (!input.isUseResource()) {
			CalculateExecutionNoResourceService calcservice = new CalculateExecutionNoResourceService();
			if (input.isByFinishDate()) {

			} else if (input.isByStartDate()) {

			}
			service.setService(calcservice);
		} else {
			service.setService(new CalculateExecutaionFirstFreeService(ResourcesDataUtils.getAllResources()));
		}

		service.calculate(template, input.getDate());
		GanttData data = service.getGantData();

		if (input.getScala() != null) {
			data.setScale(input.getScala());
		} else {
			data.setScale(ScaleConstants.DAY);
		}
		return data;
	}
}
