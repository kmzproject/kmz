package ru.kmz.web.ganttcommon.client;

import java.util.Date;

import ru.kmz.web.common.client.TreeIconProvider;
import ru.kmz.web.ganttcommon.client.data.Dependency;
import ru.kmz.web.ganttcommon.client.data.Task;

import com.gantt.client.Gantt;
import com.gantt.client.config.GanttConfig;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.dom.DomHelper;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent.CollapseItemHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.tree.Tree.Joint;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;
import com.sencha.gxt.widget.core.client.treegrid.TreeGridView;

public class CommonGantt extends Gantt<Task, Dependency> {

	public CommonGantt(TreeStore<Task> taskStore, ListStore<Dependency> dependecyStore, GanttConfig config) {
		super(taskStore, dependecyStore, config);
//		getGanttPanel().getView().setPreventScrollToTopOnRefresh(false);
	}

	@Override
	protected TreeGrid<Task> createStaticGrids(TreeStore<Task> store, ColumnModel<Task> cm) {
		TreeGrid<Task> grid = new TreeGrid<Task>(store, cm, cm.getColumn(0));
		grid.addStyleName("sch-grid");

		TreeGridView<Task> view = new TreeGridView<Task>() {
			protected void init(final Grid<Task> grid) {
				vbar = false;
				super.init(grid);
				tree.setIconProvider(new TreeIconProvider<Task>());
			}

			@Override
			public SafeHtml getTemplate(Task m, String id, SafeHtml text, ImageResource icon, boolean checkable,
					Joint joint, int level) {
				// icon = icon == null ? null : resources.folderLogo(); //Логотип папки
				return super.getTemplate(m, id, text, icon, checkable, joint, level);
			}

			@Override
			protected void processRows(int startRow, boolean skipStripe) {
				super.processRows(startRow, skipStripe);
				if (ds.size() < 1) {
					return;
				}
				NodeList<Element> rows = getRows();
				for (int i = 0, len = rows.getLength(); i < len; i++) {
					Element row = (Element) rows.getItem(i);
					String rowHeight = String.valueOf(timePanel.getOverlapCount(i) * config.rowHeight);
					((XElement) row).setHeight(rowHeight);
				}
			}

			protected void calculateVBar(boolean force) {
				if (force) {
					resize();
				}
				boolean vbar = false;
				if (force || this.vbar != vbar) {
					this.vbar = vbar;
					lastViewWidth = -1;
					layout(true);
				}
			}

			public void refresh(boolean headerToo) {
				super.refresh(headerToo);
				if (timePanel.isHorizontalScroll()) {
					StringBuilder html = new StringBuilder();
					html.append("<div class=\"sch-scrolladjust\" style=\"height:");
					html.append(XDOM.getScrollBarWidth());
					html.append("px\"></div>");
					DomHelper.append(getBody(), html.toString());
				}
			}

			protected void initElements() {
				super.initElements();
				scroller.getStyle().setOverflowY(Overflow.HIDDEN);
			}
		};

		// Temp
		view.setForceFit(true);
		view.setAdjustForHScroll(true);

		grid.setView(view);

		grid.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				refresh();
			}
		});
		CollapseItemHandler<Task> handler = new CollapseItemHandler<Task>() {
			@Override
			public void onCollapse(CollapseItemEvent<Task> event) {
				refresh();
			}
		};
		grid.addHandler(handler, CollapseItemEvent.getType());
		ExpandItemHandler<Task> expHandler = new ExpandItemHandler<Task>() {
			@Override
			public void onExpand(ExpandItemEvent<Task> event) {
				refresh();
			}
		};
		grid.addHandler(expHandler, ExpandItemEvent.getType());
		return grid;

	}

	@Override
	public Date getTaskEndDate(Task task) {
		return super.getTaskEndDate(task);
	}

}
