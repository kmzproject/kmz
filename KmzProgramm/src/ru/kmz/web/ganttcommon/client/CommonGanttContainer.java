package ru.kmz.web.ganttcommon.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.client.data.DataTransformator;
import ru.kmz.web.ganttcommon.client.data.Dependency;
import ru.kmz.web.ganttcommon.client.data.DependencyProps;
import ru.kmz.web.ganttcommon.client.data.IDemoData;
import ru.kmz.web.ganttcommon.client.data.Task;
import ru.kmz.web.ganttcommon.client.data.TaskProps;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;

import com.gantt.client.config.GanttConfig;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.scheduler.client.core.timeaxis.TimeAxisGenerator;
import com.scheduler.client.core.timeaxis.preconfig.DayTimeAxisGenerator;
import com.scheduler.client.core.timeaxis.preconfig.MonthTimeAxisGenerator;
import com.scheduler.client.core.timeaxis.preconfig.WeekTimeAxisGenerator;
import com.scheduler.client.core.timeaxis.preconfig.YearTimeAxisGenerator;
import com.scheduler.client.zone.Line;
import com.scheduler.client.zone.LineProperties;
import com.scheduler.client.zone.ZoneGeneratorInt;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CommonGanttContainer implements IsWidget {

	public CommonGanttContainer(GanttData data, GanttTaskContextMenuHandler handler) {
		this.ganttData = new DataTransformator(data);
		;
		this.scale = ScaleConstants.WEEK;
		this.handler = handler;
	}

	private IDemoData ganttData;
	protected boolean showPercentDone;
	private String scale;

	private CommonGantt gantt;

	private TreeStore<Task> dataTaskStore;
	private ListStore<Dependency> dataDepStore;
	private GanttTaskContextMenuHandler handler;
	private VerticalLayoutContainer container;

	@Override
	public Widget asWidget() {
		if (container == null) {
			createContainer();
		}
		return container;
	}

	private void createContainer() {
		// resources
		setData(ganttData);

		CommonGanttConfig config = new CommonGanttConfig();
		config.setLeftColumns(createStaticColumns());
		config.setTimeHeaderConfig(getTimeHeaders());
		config.setZoneGenerators(getZones());

		// Create the Gxt Scheduler
		gantt = new CommonGantt(dataTaskStore, dataDepStore, config);
		gantt.setLineStore(createLines());
		gantt.setContextMenuHandler(handler);

		setStartEnd();

		container = new VerticalLayoutContainer();
		container.setHeight(500);
		container.setScrollMode(ScrollMode.ALWAYS);

		container.add(gantt, new VerticalLayoutData(1, 1));
	}

	public void refresh() {
		this.gantt.refresh();
	}

	public void refreshAsNewData(GanttData ganttData) {
		this.ganttData = new DataTransformator(ganttData);
		setData(this.ganttData);
		setStartEnd();
		gantt.refresh();
	}

	public void expandAll() {
		((TreeGrid<Task>) gantt.getLeftGrid()).expandAll();
	}

	public void changeScale(String scale) {
		this.scale = scale;
		GanttConfig c = gantt.getConfig();
		c.timeHeaderConfig = getTimeHeaders();
		setStartEnd();
		gantt.refresh();
	}

	private List<ZoneGeneratorInt> getZones() {
		// Add zones to weekends
		ArrayList<ZoneGeneratorInt> zoneGenerators = new ArrayList<ZoneGeneratorInt>();
		// zoneGenerators.add(new WeekendZoneGenerator()); // Create a zone
		// every
		return zoneGenerators;
	}

	// Creating the store containing the lines
	private ListStore<Line> createLines() {
		StyleInjectorHelper.ensureInjected(CommonGanttResources.resources.css(), true);

		LineProperties lineProps = GWT.create(LineProperties.class);
		ListStore<Line> store = new ListStore<Line>(lineProps.key());
		String customCssStyle = CommonGanttResources.resources.css().todayLineMain();
		Line line = new Line(new Date(), "Текушая дата :" + new Date().toString(), customCssStyle);
		store.add(line);
		return store;
	}

	private void setStartEnd() {
		DateWrapper dwStart = new DateWrapper(ganttData.getDateStart()).clearTime();
		DateWrapper dwFinish = new DateWrapper(ganttData.getDateFinish()).clearTime();
		int delta = 10;
		// Set start and end date.
		if (scale.equals(ScaleConstants.DAY)) {
			gantt.setStartEnd(dwStart.addDays(-delta).asDate(), dwFinish.addDays(delta * 2).asDate());
		} else if (scale.equals(ScaleConstants.MONTH)) {
			gantt.setStartEnd(dwStart.addMonths(-delta).asDate(), dwFinish.addMonths(delta * 2).asDate());
		} else if (scale.equals(ScaleConstants.WEEK)) {
			gantt.setStartEnd(dwStart.addDays(-delta * 7).asDate(), dwFinish.addDays(delta * 14).asDate());
		}
	}

	private ArrayList<TimeAxisGenerator> getTimeHeaders() {
		ArrayList<TimeAxisGenerator> headers = new ArrayList<TimeAxisGenerator>();
		if (scale.equals(ScaleConstants.DAY)) {
			headers.add(new WeekTimeAxisGenerator("dd MMM"));
			headers.add(new DayTimeAxisGenerator("EEE"));
		} else if (scale.equals(ScaleConstants.MONTH)) {
			headers.add(new YearTimeAxisGenerator("yyyy"));
			headers.add(new MonthTimeAxisGenerator("MMM"));
		} else if (scale.equals(ScaleConstants.WEEK)) {
			headers.add(new MonthTimeAxisGenerator("MMM dd"));
			headers.add(new WeekTimeAxisGenerator("dd/MM"));
		}
		return headers;
	}

	private void setData(IDemoData data) {
		if (dataTaskStore == null) {
			dataTaskStore = new TreeStore<Task>(TaskProps.props.key());
		} else {
			dataTaskStore.clear();
		}
		Task root = data.getTasks();

		for (Task base : root.getChildren()) {
			dataTaskStore.add(base);
			if (base.hasChildren()) {
				processFolder(base);
			}
		}

		dataDepStore = new ListStore<Dependency>(DependencyProps.depProps.key());
		dataDepStore.addAll(data.getDependencies());
	}

	// Creates the static columns
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ColumnModel<Task> createStaticColumns() {
		List<ColumnConfig<Task, ?>> configs = new ArrayList<ColumnConfig<Task, ?>>();

		ColumnConfig<Task, ?> column = new ColumnConfig<Task, String>(TaskProps.props.name());
		column.setHeader("Работа");
		column.setWidth(160);
		column.setSortable(true);
		column.setResizable(true);
		configs.add(column);

		ColumnConfig<Task, Date> column2 = new ColumnConfig<Task, Date>(TaskProps.props.startDateTime());
		column2.setHeader("Начало");
		column2.setWidth(90);
		column2.setSortable(true);
		column2.setResizable(true);
		column2.setCell(new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd")));
		configs.add(column2);

		ColumnConfig<Task, Integer> column3 = new ColumnConfig<Task, Integer>(TaskProps.props.duration());
		column3.setHeader("Продолж.");
		column3.setWidth(70);
		column3.setSortable(true);
		column3.setResizable(true);
		configs.add(column3);

		if (showPercentDone) {
			ColumnConfig<Task, Integer> column4 = new ColumnConfig<Task, Integer>(TaskProps.props.percentDone());
			column4.setHeader("Вып. %");
			column4.setWidth(60);
			column4.setSortable(true);
			column4.setResizable(true);
			configs.add(column4);
		}

		ColumnModel cm = new ColumnModel(configs);
		cm.addHeaderGroup(0, 0, new HeaderGroupConfig("Описание работ", 1, configs.size()));

		return cm;
	}

	/** Этот механизм так же полностью раскрыват все узлы */
	public void setFilterResourceType(String resourceType) {
		if (resourceType != null) {
			if (dataTaskStore.getFilters() != null) {
				dataTaskStore.getFilters().clear();
			}
			StoreFilter<Task> filter = null;
			if (ResourceTypesConsts.ORDER.equals(resourceType)) {
				filter = new StoreFilter<Task>() {
					@Override
					public boolean select(Store<Task> store, Task parent, Task item) {
						return !item.getResourceType().equals(ResourceTypesConsts.ORDER);
					}
				};
			} else if (ResourceTypesConsts.ASSEMBLAGE.equals(resourceType)) {
				filter = new StoreFilter<Task>() {
					@Override
					public boolean select(Store<Task> store, Task parent, Task item) {
						return !item.getResourceType().equals(ResourceTypesConsts.ORDER) && !item.getResourceType().equals(ResourceTypesConsts.PREPARE)
								&& !item.getResourceType().equals(ResourceTypesConsts.ASSEMBLAGE);
					}
				};
			}
			dataTaskStore.addFilter(filter);
			dataTaskStore.setEnableFilters(true);
		} else {
			dataTaskStore.setEnableFilters(false);
		}
		gantt.refresh();
	}

	public void removeAllTaskByResourceType(String resourceType) {
		recRemoveTasks(resourceType, this.ganttData.getTasks());
	}

	private void recRemoveTasks(String resource, Task task) {
		if (task.getResourceType() != null && task.getResourceType().equals(resource)) {
			dataTaskStore.remove(task);
		} else {
			for (Task t : task.getChildren()) {
				recRemoveTasks(resource, t);
			}
		}

	}

	private void processFolder(Task folder) {
		for (Task child : folder.getChildren()) {
			dataTaskStore.add(folder, child);
			if (child.hasChildren()) {
				processFolder(child);
			}
		}
	}
}
