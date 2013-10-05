package ru.kmz.web.calculator.server;

import java.util.List;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.ResourcesDataUtils;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.gant.GantCalculationNoReourcesService;
import ru.kmz.server.engine.calculation.gant.GantCalculationResourcesService;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CalculatorModuleServiceImpl extends RemoteServiceServlet implements CalculatorModuleService {

	private Template getTemplate(CalculatorInputDataProxy input) {
		List<Template> list = TemplateDataUtils.getAllTemplates();
		return TemplateDataUtils.getTemplate(list.get(0).getKeyStr());
	}

	private boolean isValid(CalculatorInputDataProxy input) {
		return input.getDate() != null;
	}

	@Override
	public GanttData getGantResultData(CalculatorInputDataProxy input) {
		if (!isValid(input)) {
			return GanttData.getError("Не верный формат даты");
		}
		Template template = getTemplate(input);
		GanttData data = null;
		if (input.isByFinishDate()) {
			GantCalculationNoReourcesService service = new GantCalculationNoReourcesService();
			service.calculateFinish(template, input.getDate());
			data = service.getGantData();
		} else if (input.isByStartDate()) {
			GantCalculationResourcesService service = new GantCalculationResourcesService();
			service.setResourcesList(ResourcesDataUtils.getAllResources());
			service.calculateStart(template, input.getDate());
			data = service.getGantData();
		}

		if (input.getScala() != null) {
			data.setScale(input.getScala());
		} else {
			data.setScale(ScaleConstants.DAY);
		}
		return data;
	}
}
