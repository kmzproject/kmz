package ru.kmz.web.projectschart.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.projectschart.client.ProjectsChartService;
import ru.kmz.web.projectschart.shared.FunctioningCapacityParams;
import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ProjectsChartServiceImpl extends RemoteServiceServlet implements ProjectsChartService {

	@Override
	public List<FunctioningCapacityProxy> getFunctioningCapacity(FunctioningCapacityParams params) {
		params.setFrom(DateUtils.getDateNoTime(params.getFrom()));
		params.setTo(DateUtils.getDateNoTime(params.getTo()));

		if (params.getFrom().after(params.getTo())) {
			throw new IllegalArgumentException("Дата завершения должна быть после даты начала");
		}

		List<ProductElementTask> tasks = ProductElementTaskDataUtils.getTasksByDate(params.getFrom(), params.getTo(), params.getResourceType());

		List<FunctioningCapacityProxy> list = new ArrayList<FunctioningCapacityProxy>();
		Date date = params.getFrom();
		while (date.before(params.getTo())) {
			int activitiesCount = 0;
			for (ProductElementTask task : tasks) {
				if ((task.getStart().before(date) || task.getStart().equals(date)) && (task.getFinish().after(date))) {
					activitiesCount++;
				}
			}
			list.add(new FunctioningCapacityProxy(DateUtils.dateToString(date), activitiesCount));
			date = DateUtils.getOffsetDate(date, 1);
		}

		return list;
	}
}
