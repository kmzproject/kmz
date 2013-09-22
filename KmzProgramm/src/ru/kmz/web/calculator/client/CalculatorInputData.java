package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class CalculatorInputData implements IsWidget {

	private FlowLayoutContainer container;

	private DateField finishField;

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

		finishField = new DateField();
		finishField.setAutoValidate(true);
		container.add(new FieldLabel(finishField, "Дата"));

		TextButton calculate = new TextButton("Расчитать");
		calculate.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				CalculatorInputDataProxy input = new CalculatorInputDataProxy();
				input.setFinishDate(finishField.getValue());
				module.showResult(input);
			}
		});
		container.add(calculate);
	}
}
