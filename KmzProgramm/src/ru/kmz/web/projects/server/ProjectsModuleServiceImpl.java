package ru.kmz.web.projects.server;

import java.util.Date;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.gant.GanttProjectsService;
import ru.kmz.server.engine.projects.GetOrdersService;
import ru.kmz.server.engine.projects.NewProjectService;
import ru.kmz.server.engine.projects.UpdateProductElementTaskService;
import ru.kmz.server.engine.resources.CalculateExecutionNoResourceService;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.client.ProjectsModuleService;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;

@SuppressWarnings("serial")
public class ProjectsModuleServiceImpl extends AbstractServiceImpl implements ProjectsModuleService {

	@Override
	public GanttData getCurrentTasks(GanttDataFilter filter) {
		GanttProjectsService service = new GanttProjectsService();

		service.setGetOrdersService(new GetOrdersService());
		service.calculate();
		GanttData data = service.getGantData();
		return data;
	}

	public void save(CalculatorInputDataProxy input) {
		NewProjectService service = new NewProjectService();

		String orderId = input.getOrderId();
		Order order = OrderDataUtils.getOrder(orderId);
		service.setOrder(order);
		service.setCount(input.getCount());
		CalculateExecutionNoResourceService calculateService = new CalculateExecutionNoResourceService();
		calculateService.setUseWeekend(input.isUseWeekend());
		service.setService(calculateService);

		Template template = TemplateDataUtils.getTemplate(input.getTemplateId());
		Date date = DateUtils.getDateNoTime(input.getDate());
		ProductElementTask task = service.save(template, date);
		HistoryUtils.addProductToOrder(task);

	}

	@Override
	public void deleteProduct(String id) {
		ProductElementTaskDataUtils.deleteProduct(id);
	}

	@Override
	public void updateDate(String id, Date date) {
		UpdateProductElementTaskService service = new UpdateProductElementTaskService(id, date);
		service.update();
	}

}
