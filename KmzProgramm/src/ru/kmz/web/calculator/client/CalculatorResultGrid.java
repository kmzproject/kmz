package ru.kmz.web.calculator.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.calculator.shared.CalculatorResultRowProxy;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.tips.QuickTip;

public class CalculatorResultGrid implements IsWidget {

	private static final CalculatorResultRowProxyProperties props = GWT
			.create(CalculatorResultRowProxyProperties.class);

	private ContentPanel root;

	private List<CalculatorResultRowProxy> rows;

	public void setRows(List<CalculatorResultRowProxy> rows) {
		this.rows = rows;
	}

	@Override
	public Widget asWidget() {
		if (root == null) {

			ColumnConfig<CalculatorResultRowProxy, String> nameCol = new ColumnConfig<CalculatorResultRowProxy, String>(
					props.name(), 50, "Название");
			ColumnConfig<CalculatorResultRowProxy, Date> startDate = new ColumnConfig<CalculatorResultRowProxy, Date>(
					props.startDate(), 100, "Дата начала работ");
			ColumnConfig<CalculatorResultRowProxy, Date> finishDate = new ColumnConfig<CalculatorResultRowProxy, Date>(
					props.finishDate(), 100, "Дата окончания работ");
			startDate.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));
			finishDate.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

			List<ColumnConfig<CalculatorResultRowProxy, ?>> l = new ArrayList<ColumnConfig<CalculatorResultRowProxy, ?>>();
			l.add(nameCol);
			l.add(startDate);
			l.add(finishDate);
			ColumnModel<CalculatorResultRowProxy> cm = new ColumnModel<CalculatorResultRowProxy>(l);

			ListStore<CalculatorResultRowProxy> store = new ListStore<CalculatorResultRowProxy>(props.key());
			store.addAll(rows);

			root = new ContentPanel();
			root.setHeadingText("Результат расчетов");
			// root.getHeader().setIcon(ExampleImages.INSTANCE.table());
			root.setPixelSize(600, 500);
			root.addStyleName("margin-10");

			new Resizable(root, Dir.E, Dir.SE, Dir.S);

			final Grid<CalculatorResultRowProxy> grid = new Grid<CalculatorResultRowProxy>(store, cm);
			grid.getView().setAutoExpandColumn(nameCol);
			grid.getView().setStripeRows(true);
			grid.getView().setColumnLines(true);
			grid.setBorders(false);

			grid.setColumnReordering(true);
			grid.setStateful(true);
			grid.setStateId("gridExample");

			// GridStateHandler<CalculatorResultRowProxy> state = new
			// GridStateHandler<CalculatorResultRowProxy>(grid);
			// state.loadState();

			VerticalLayoutContainer con = new VerticalLayoutContainer();
			root.setWidget(con);
			con.add(grid, new VerticalLayoutData(1, 1));

			// needed to enable quicktips (qtitle for the heading and qtip for
			// the
			// content) that are setup in the change GridCellRenderer
			new QuickTip(grid);
		}

		return root;
	}
}
