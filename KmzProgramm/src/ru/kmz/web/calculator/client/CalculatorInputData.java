package ru.kmz.web.calculator.client;

import java.util.Date;

import ru.kmz.web.calculator.client.window.SelectOrderWindow;
import ru.kmz.web.calculator.shared.CalculatorInputDataProxy;
import ru.kmz.web.common.client.data.KeyValueData;
import ru.kmz.web.common.client.data.KeyValueDataProperties;
import ru.kmz.web.common.client.window.IUpdatableWithValue;
import ru.kmz.web.ganttcommon.shared.ScaleConstants;
import ru.kmz.web.template.client.TemplateSelectWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.info.Info;

public class CalculatorInputData implements IsWidget, IUpdatableWithValue<KeyValueData> {

	private FlowLayoutContainer container;
	private CalculateHandler handler;
	private String templateId;
	private Label templateName;
	private TextButton calculate;
	private TextButton addButton;
	private Radio radioFinish;
	private Radio radioStart;
	private CheckBox checkBoxShowOtherTasks;
	private CheckBox checkBoxResources;
	private DateField dataField;
	private ComboBox<KeyValueData> scalaCombo;

	public CalculatorInputData(CalculateHandler handler) {
		this.handler = handler;
	}

	@Override
	public Widget asWidget() {
		if (container == null) {
			createContainer();
		}
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

		scalaCombo = new ComboBox<KeyValueData>(list, KeyValueDataProperties.prop.value());
		scalaCombo.setForceSelection(true);
		scalaCombo.setTypeAhead(true);
		scalaCombo.setTriggerAction(TriggerAction.ALL);
		scalaCombo.setEditable(false);
		scalaCombo.setValue(list.get(1));

		container.add(new FieldLabel(scalaCombo, "Масштаб"));

		checkBoxResources = new CheckBox();
		checkBoxResources.setBoxLabel("Использовать ресурсы в расчетах");
		checkBoxResources.setEnabled(false);
		checkBoxResources.setValue(false);
		container.add(checkBoxResources);

		checkBoxShowOtherTasks = new CheckBox();
		checkBoxShowOtherTasks.setBoxLabel("Отображать загрузку предприятия");
		checkBoxShowOtherTasks.setEnabled(false);
		checkBoxShowOtherTasks.setValue(false);
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
		TextButton selectButton = new TextButton("Выбрать");
		selectButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				TemplateSelectWindow window = new TemplateSelectWindow();
				window.setUpdatable(CalculatorInputData.this);
				window.show();
			}
		});
		templatePanel.add(templateName);
		templatePanel.add(selectButton);

		container.add(new FieldLabel(templatePanel, "Шаблон"));

		calculate = new TextButton("Расчитать");
		calculate.setEnabled(false);
		calculate.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (templateId == null) {
					Info.display("Ошибка", "Необходимо выбрать шаблон");
					return;
				}
				CalculatorInputDataProxy input = getInput();
				handler.onCalculate(input);
			}
		});

		container.add(calculate);

		addButton = new TextButton("Сохранить");
		addButton.setEnabled(false);
		addButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				SelectOrderWindow window = new SelectOrderWindow();
				window.setUpdatable(new UpdatebleOnSave());
				window.show();
			}
		});

		container.add(addButton);

	}

	public static interface CalculateHandler {
		void onCalculate(CalculatorInputDataProxy data);
	}

	@Override
	public void update(KeyValueData value) {
		templateName.setText(value.getValue());
		templateId = value.getKey();
		calculate.setEnabled(true);
		addButton.setEnabled(true);
	}

	private CalculatorInputDataProxy getInput() {
		CalculatorInputDataProxy input = new CalculatorInputDataProxy();
		input.setDate(dataField.getValue());
		input.setByFinishDate(radioFinish.getValue());
		input.setByStartDate(radioStart.getValue());
		input.setScala(scalaCombo.getValue().getKey());
		input.setTemplateId(templateId);
		input.setUseResource(checkBoxResources.getValue());
		input.setShowOtherTasks(checkBoxShowOtherTasks.getValue());
		return input;
	}

	private class UpdatebleOnSave implements IUpdatableWithValue<KeyValueData> {

		@Override
		public void update(KeyValueData value) {
			CalculatorInputDataProxy input = getInput();
			CalculatorModuleView.getService().save(input, value.getKey(), new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					Info.display("Сохранены", "Успешно сохранил");
				}

				@Override
				public void onFailure(Throwable caught) {
					Info.display("Ошибка", "Ошибка загрузки" + caught);
				}
			});
		}

	}
}
