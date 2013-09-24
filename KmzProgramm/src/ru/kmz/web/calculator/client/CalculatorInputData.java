package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Radio;

public class CalculatorInputData implements IsWidget {

	private FlowLayoutContainer container;

	private DateField dataField;

	private CalculatorModuleView module;

	public CalculatorInputData(CalculatorModuleView module) {
		this.module = module;
	}

	@Override
	public Widget asWidget() {
		if (container == null)
			createContainer();

		return container;
	}

	private void createContainer() {
		container = new FlowLayoutContainer();

		dataField = new DateField();
		dataField.setAutoValidate(true);
		container.add(new FieldLabel(dataField, "Дата"));

		final Radio radioFinish = new Radio();
		radioFinish.setBoxLabel("По дате завершения");
		radioFinish.setValue(true);
		container.add(radioFinish);

		final Radio radioStart = new Radio();
		radioStart.setBoxLabel("По дате начала");
		container.add(radioStart);

		ToggleGroup group = new ToggleGroup();
		group.add(radioFinish);
		group.add(radioStart);

		TextButton calculate = new TextButton("Расчитать");
		calculate.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				CalculatorInputDataProxy input = new CalculatorInputDataProxy();
				input.setDate(dataField.getValue());
				input.setByFinishDate(radioFinish.getValue());
				input.setByStartDate(radioStart.getValue());
				module.showResult(input);
			}
		});
		container.add(calculate);
	}
}
