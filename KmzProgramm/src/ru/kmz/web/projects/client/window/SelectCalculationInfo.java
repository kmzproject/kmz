package ru.kmz.web.projects.client.window;

import java.util.Date;

import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.window.CommonSelectWindow;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.projects.shared.CalculatorInputDataProxy;
import ru.kmz.web.templatecommon.client.window.TemplateSelectWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Radio;

public class SelectCalculationInfo extends CommonSelectWindow<CalculatorInputDataProxy> implements IUpdatableWithValue<KeyValueData> {

	private String templateId;
	private Label templateName;
	private Radio radioFinish;
	private Radio radioStart;
	private CheckBox checkBoxShowOtherTasks;
	private CheckBox checkBoxResources;
	private DateField dataField;

	public SelectCalculationInfo() {
		super();
		setPixelSize(370, 220);
		selectButton.setEnabled(false);
	}

	@Override
	protected Container getInfoContainer() {
		FlowLayoutContainer container = new FlowLayoutContainer();

		dataField = new DateField();
		dataField.setAutoValidate(true);
		dataField.setValue(new Date());
		container.add(new FieldLabel(dataField, "Дата"));

		checkBoxResources = new CheckBox();
		checkBoxResources.setBoxLabel("Использовать ресурсы в расчетах");
		checkBoxResources.setEnabled(false);
		checkBoxResources.setValue(false);
		container.add(checkBoxResources);

		checkBoxShowOtherTasks = new CheckBox();
		checkBoxShowOtherTasks.setBoxLabel("Отображать загрузку предприятия");
		checkBoxShowOtherTasks.setEnabled(true);
		checkBoxShowOtherTasks.setValue(true);
		container.add(checkBoxShowOtherTasks);

		radioFinish = new Radio();
		radioFinish.setBoxLabel("По дате завершения");
		radioFinish.setValue(true);
		container.add(radioFinish);

		radioStart = new Radio();
		radioStart.setBoxLabel("По дате начала");
		container.add(radioStart);

		ToggleGroup group = new ToggleGroup();
		group.add(radioFinish);
		group.add(radioStart);

		HorizontalPanel templatePanel = new HorizontalPanel();
		templatePanel.setSpacing(10);

		templateName = new Label();
		TextButton selectTemplateButton = new TextButton("Выбрать");
		selectTemplateButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				TemplateSelectWindow window = new TemplateSelectWindow();
				window.setUpdatable(SelectCalculationInfo.this);
				window.show();
			}
		});
		templatePanel.add(templateName);
		templatePanel.add(selectTemplateButton);

		container.add(new FieldLabel(templatePanel, "Шаблон"));

		return container;
	}

	private CalculatorInputDataProxy getInput() {
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(dataField.getValue());
		input.setByFinishDate(radioFinish.getValue());
		input.setByStartDate(radioStart.getValue());
		input.setTemplateId(templateId);
		input.setUseResource(checkBoxResources.getValue());
		input.setShowOtherTasks(checkBoxShowOtherTasks.getValue());
		return input;
	}

	@Override
	protected CalculatorInputDataProxy getSelectedValue() {
		CalculatorInputDataProxy input = getInput();
		return input;

	}

	@Override
	public void update(KeyValueData value) {
		templateName.setText(value.getValue());
		templateId = value.getKey();
		selectButton.setEnabled(true);
	}

}
