package ru.kmz.web.projects.server;

import java.util.Date;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.OrderDataUtils;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.data.utils.TemplateDataUtils;
import ru.kmz.server.engine.gant.GanttProjectsService;
import ru.kmz.server.engine.projects.CreateNewProductService;
import ru.kmz.server.engine.projects.GetOrdersService;
import ru.kmz.server.engine.projects.UpdateProductElementTaskService;
import ru.kmz.server.engine.projects.update.UpdateDoneValueService;
import ru.kmz.server.services.AbstractServiceImpl;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.server.utils.HistoryUtils;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.projects.client.ProjectsModuleService;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.projects.shared.GanttDataFilter;
import ru.kmz.web.projects.shared.UpdateProjectElementTaskParams;

@SuppressWarnings("serial")
public class ProjectsModuleServiceImpl extends AbstractServiceImpl implements ProjectsModuleService {

	@Override
	public GanttData getCurrentTasks(GanttDataFilter filter) {
		if (filter == null) {
			filter = new GanttDataFilter();
		}

		GetOrdersService getOrdersService = new GetOrdersService();
		getOrdersService.setOrderIds(filter.getOrderIds());

		GanttProjectsService service = new GanttProjectsService();

		service.setGetOrdersService(getOrdersService);
		service.calculate();
		GanttData data = service.getGantData();
		return data;
	}

	public void save(CalculatorInputDataProxy input) {
		CreateNewProductService service = new CreateNewProductService();

		long orderId = input.getOrderId();
		Order order = OrderDataUtils.getOrder(orderId);
		service.setOrder(order);
		service.setCount(input.getCount());
		service.setUseWeekend(input.isUseWeekend());

		Template template = TemplateDataUtils.getTemplate(input.getTemplateId());
		Date date = DateUtils.getDateNoTime(input.getDate());

		ProductElementTask task = service.save(template, date);

		HistoryUtils.addProductToOrder(task);
	}

	@Override
	public void deleteProduct(long id) {
		ProductElementTask task = ProductElementTaskDataUtils.getTask(id);
		HistoryUtils.addDeleteProductElementTask(task);
		long orderId = task.getOrderId();
		ProductElementTaskDataUtils.deleteProduct(id);
		UpdateDoneValueService updateDoneService = new UpdateDoneValueService();
		updateDoneService.updateByOrderId(orderId);
	}

	@Override
	public void updateDate(long id, UpdateProjectElementTaskParams params) {
		Date finishDate = params.getFinishDate();
		UpdateProductElementTaskService service = new UpdateProductElementTaskService(id, finishDate);
		service.update();
	}

}
