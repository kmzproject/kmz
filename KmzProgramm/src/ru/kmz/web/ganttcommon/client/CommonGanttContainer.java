package ru.kmz.web.ganttcommon.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.ganttcommon.client.data.DataTransformator;
import ru.kmz.web.ganttcommon.client.data.Dependency;
import ru.kmz.web.ganttcommon.client.data.DependencyProps;
import ru.kmz.web.ganttcommon.client.data.IDemoData;
import ru.kmz.web.ganttcommon.client.data.Task;
import ru.kmz.web.ganttcommon.client.data.TaskProps;
import ru.kmz.web.ganttcommon.shared.GanttData;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;

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
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CommonGanttContainer implements IsWidget {

	public CommonGanttContainer(GanttData data) {
		this.ganttData = data;
	}

	private GanttData ganttData;
	protected boolean showPercentDone;

	private CommonGantt gantt;
	ListStore<Task> taskStore;

	private TreeStore<Task> dataTaskStore;
	private ListStore<Dependency> dataDepStore;

	@Override
	public Widget asWidget() {
		// resources
		IDemoData data = new DataTransformator(ganttData);
		setData(data);

		CommonGanttConfig config = new CommonGanttConfig();
		config.setLeftColumns(createStaticColumns());
		config.setTimeHeaderConfig(getTimeHeaders());
		config.setZoneGenerators(getZones());

		// Create the Gxt Scheduler
		gantt = new CommonGantt(dataTaskStore, dataDepStore, config);
		gantt.setLineStore(createLines());

		setStartEnd();

		ContentPanel cp = new ContentPanel();
		cp.setHeadingText(ganttData.getName());
		cp.setPixelSize(1000, 460);
		cp.getElement().setMargins(new Margins(5));

		VerticalLayoutContainer vc = new VerticalLayoutContainer();
		vc.add(createToolBar(), new VerticalLayoutData(1, -1));
		vc.add(gantt, new VerticalLayoutData(1, 1));
		cp.setWidget(vc);
		return cp;
	}

	private ToolBar createToolBar() {
		ToolBar tbar = new ToolBar();
		TextButton showAll = new TextButton("Раскрыть все");
		showAll.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				((TreeGrid<Task>) gantt.getLeftGrid()).expandAll();
			}
		});
		tbar.add(showAll);
		return tbar;
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
		int delta = 4;
		// Set start and end date.
		String scale = ganttData.getScale();
		if (scale.equals(ScaleConstants.DAY)) {
			gantt.setStartEnd(dwStart.addDays(-delta).asDate(), dwFinish.addDays(delta).asDate());
		} else if (scale.equals(ScaleConstants.MONTH)) {
			gantt.setStartEnd(dwStart.addMonths(-delta).asDate(), dwFinish.addMonths(delta).asDate());
		} else if (scale.equals(ScaleConstants.WEEK)) {
			gantt.setStartEnd(dwStart.addDays(-delta * 7).asDate(), dwFinish.addDays(delta * 7).asDate());
		}
	}

	private ArrayList<TimeAxisGenerator> getTimeHeaders() {
		ArrayList<TimeAxisGenerator> headers = new ArrayList<TimeAxisGenerator>();
		String scale = ganttData.getScale();
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
		dataTaskStore = new TreeStore<Task>(TaskProps.props.key());
		Task root = data.getTasks();
		for (Task base : root.getChildren()) {
			dataTaskStore.add(base);
			if (base.hasChildren()) {
				processFolder(dataTaskStore, base);
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

	private void processFolder(TreeStore<Task> store, Task folder) {
		for (Task child : folder.getChildren()) {
			store.add(folder, child);
			if (child.hasChildren()) {
				processFolder(store, child);
			}
		}
	}
}
