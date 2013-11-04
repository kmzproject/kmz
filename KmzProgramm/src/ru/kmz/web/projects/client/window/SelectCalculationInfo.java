package ru.kmz.web.projects.client.window;

import java.text.ParseException;
import java.util.Date;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.CommonSelectWindow;
import ru.kmz.web.ordercommon.client.control.OrderComboBoxUtils;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.templatecommon.client.control.TemplateComboBoxUtils;

import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class SelectCalculationInfo extends CommonSelectWindow<CalculatorInputDataProxy> {

	private DateField dataField;
	private ComboBox<KeyValueData> templateComboBox;
	private ComboBox<KeyValueData> orderComboBox;
	private TextField countField;
	private CheckBox useWeekend;

	public SelectCalculationInfo() {
		super();
		setPixelSize(370, 200);
	}

	@Override
	protected Container getInfoContainer() {
		FlowLayoutContainer container = new FlowLayoutContainer();

		dataField = new DateField();
		dataField.setAutoValidate(true);
		dataField.setValue(new Date());
		container.add(new FieldLabel(dataField, "Дата"));

		HorizontalPanel hp = new HorizontalPanel();
		container.add(hp);

		templateComboBox = TemplateComboBoxUtils.createTemplateComboBox();
		container.add(new FieldLabel(templateComboBox, "Шаблон"));

		orderComboBox = OrderComboBoxUtils.createOrderCompoBox(true);
		container.add(new FieldLabel(orderComboBox, "Заказ"));

		countField = new TextField();
		countField.setValue("1");
		container.add(new FieldLabel(countField, "Количество"));

		useWeekend = new CheckBox();
		useWeekend.setValue(true);
		container.add(new FieldLabel(useWeekend, "Учитывать выходные"));

		return container;
	}

	private CalculatorInputDataProxy getInput() {
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(dataField.getValue());
		input.setTemplateId(templateComboBox.getValue().getKey());
		if (orderComboBox.getValue() != null) {
			input.setOrderId(orderComboBox.getValue().getKey());
		}
		try {
			int count = IntegerParser.instance().parse(countField.getValue());
			input.setCount(count);
		} catch (ParseException ex) {
			Info.display("Ошибка", "Не верный формат данных");
			return null;
		}
		input.setUseWeekend(useWeekend.getValue());
		return input;
	}

	@Override
	protected CalculatorInputDataProxy getSelectedValue() {
		CalculatorInputDataProxy input = getInput();
		return input;

	}
}
