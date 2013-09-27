package ru.kmz.web.gant.client;

import ru.kmz.web.calculator.client.CalculatorInputData;
import ru.kmz.web.calculator.client.CalculatorInputData.CalculateHandler;
import ru.kmz.web.calculator.client.CalculatorModuleView;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.common.client.IKmzModule;
import ru.kmz.web.common.shared.gant.GanttData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.info.Info;

public class GantModuleView implements EntryPoint, IsWidget, IKmzModule, CalculateHandler {

	private static GantModuleView instance;
	private VerticalPanel container;
	private HorizontalPanel gantContainer;

	@Override
	public void onModuleLoad() {
		instance = this;
		if (RootPanel.get("gant") != null)
			RootPanel.get("gant").add(asWidget());
	}

	@Override
	public String getModuleName() {
		return "Модуль расчетов гант";
	}

	@Override
	public Widget asWidget() {
		if (container == null) {
			createContainer();
		}
		return container;
	}

	private void createContainer() {
		container = new VerticalPanel();
		container.setSpacing(10);

		CalculatorInputData input = new CalculatorInputData(this);
		gantContainer = new HorizontalPanel();
		container.add(input);
		container.add(gantContainer);
	}

	public static GantModuleView getInstance() {
		if (instance == null)
			instance = new GantModuleView();
		return instance;
	}

	@Override
	public void onCalculate(CalculatorInputDataProxy data) {
		CalculatorModuleView.getService().getGantResultData(data, new AsyncCallback<GanttData>() {

			@Override
			public void onSuccess(GanttData result) {
				CalculationTemplateGant gant = new CalculationTemplateGant(result);
				gantContainer.clear();
				gantContainer.add(gant);
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", "This is error " + caught);
			}
		});
	}

}
