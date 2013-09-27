package ru.kmz.web.calculator.server;

import java.util.Calendar;
import java.util.List;

import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.calculation.CalculationByFinish;
import ru.kmz.server.engine.calculation.CalculationByStart;
import ru.kmz.web.calculator.client.CalculatorModuleService;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.gant.shared.gant.GanttData;
import ru.kmz.web.gant.shared.gant.ScaleConstants;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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

	@Override
	public GanttData getGantResultData(CalculatorInputDataProxy input) {
		GanttData data = new GanttData();
		data.setName("Test");
		data.setScale(ScaleConstants.DAY);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		calendar.add(Calendar.DATE, -10);
		data.setDateStart(calendar.getTime());
		calendar.add(Calendar.DATE, 10 + 10);
		data.setDateFinish(calendar.getTime());
		return data;
	}

}
