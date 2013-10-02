package ru.kmz.web.calculator.client;

import java.util.Date;

import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Radio;

public class CalculatorInputData implements IsWidget {

	private FlowLayoutContainer container;

	private DateField dataField;

	private CalculateHandler handler;

	public CalculatorInputData(CalculateHandler handler) {
		this.handler = handler;
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
		dataField.setValue(new Date());
		container.add(new FieldLabel(dataField, "Дата"));

		ListStore<KeyValueData> list = new ListStore<KeyValueData>(KeyValueDataProperties.prop.key());
		list.add(new KeyValueData(ScaleConstants.DAY, "День"));
		list.add(new KeyValueData(ScaleConstants.WEEK, "Неделя"));
		list.add(new KeyValueData(ScaleConstants.MONTH, "Месяц"));

		final ComboBox<KeyValueData> scalaCombo = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		scalaCombo.setForceSelection(true);
		scalaCombo.setTypeAhead(true);
		scalaCombo.setTriggerAction(TriggerAction.ALL);
		scalaCombo.setEditable(false);
		scalaCombo.setValue(list.get(0));
		container.add(scalaCombo);

		container.add(new FieldLabel(scalaCombo, "Масштаб"));

		final Radio radioFinish = new Radio();
		radioFinish.setBoxLabel("По дате завершения (без ресурсов)");
		radioFinish.setValue(true);
		container.add(radioFinish);

		final Radio radioStart = new Radio();
		radioStart.setBoxLabel("По дате начала (с ресурсами)");
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
				input.setScala(scalaCombo.getValue().getKey());
				handler.onCalculate(input);
			}
		});

		container.add(calculate);
	}

	public static interface CalculateHandler {
		void onCalculate(CalculatorInputDataProxy data);
	}
}
