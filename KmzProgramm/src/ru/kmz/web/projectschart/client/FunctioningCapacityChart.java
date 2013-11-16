package ru.kmz.web.projectschart.client;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.web.projectschart.shared.FunctioningCapacityProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class FunctioningCapacityChart implements IsWidget, EntryPoint {

	public interface DataPropertyAccess extends PropertyAccess<FunctioningCapacityProxy> {
		ValueProvider<FunctioningCapacityProxy, Integer> activitiesCount();

		ValueProvider<FunctioningCapacityProxy, String> day();

		@Path("day")
		ModelKeyProvider<FunctioningCapacityProxy> nameKey();
	}

	private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

	private ListStore<FunctioningCapacityProxy> store;
	private Chart<FunctioningCapacityProxy> chart;

	public void setData(List<FunctioningCapacityProxy> list) {
		store.clear();
		store.addAll(list);
		chart.redrawChart();
	}

	public FunctioningCapacityChart() {
		store = new ListStore<FunctioningCapacityProxy>(dataAccess.nameKey());
		List<FunctioningCapacityProxy> list = new ArrayList<FunctioningCapacityProxy>();
		store.addAll(list);

		chart = new Chart<FunctioningCapacityProxy>();
		chart.setStore(store);
		chart.setShadowChart(true);
	}

	@Override
	public Widget asWidget() {

		NumericAxis<FunctioningCapacityProxy> axis = new NumericAxis<FunctioningCapacityProxy>();
		axis.setMaximum(10);
		axis.setMinimum(0);
		axis.setPosition(Position.LEFT);
		axis.addField(dataAccess.activitiesCount());
		TextSprite title = new TextSprite("Количество активностей");
		title.setFontSize(18);
		axis.setTitleConfig(title);
		axis.setDisplayGrid(true);
		axis.setWidth(50);
		chart.addAxis(axis);

		CategoryAxis<FunctioningCapacityProxy, String> catAxis = new CategoryAxis<FunctioningCapacityProxy, String>();
		catAxis.setPosition(Position.BOTTOM);
		catAxis.setField(dataAccess.day());
		title = new TextSprite("Дата");
		title.setFontSize(18);
		catAxis.setTitleConfig(title);
		chart.addAxis(catAxis);

		final BarSeries<FunctioningCapacityProxy> column = new BarSeries<FunctioningCapacityProxy>();
		column.setYAxisPosition(Position.LEFT);
		column.addYField(dataAccess.activitiesCount());
		column.addColor(new RGB(148, 174, 10));
		column.setColumn(true);
		chart.addSeries(column);

		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.setHeight(500);

		chart.setLayoutData(new VerticalLayoutData(1, 1));
		layout.add(chart);

		return layout;
	}

	public void onModuleLoad() {
		RootPanel.get().add(asWidget());
	}
}