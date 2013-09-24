package ru.kmz.web.calculator.client;

import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.common.client.DateUtils;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class CalculatorResult implements IsWidget {

	private FlowLayoutContainer container;
	private CalculatorResultGrid resultGrid;
	private Label label;

	@Override
	public Widget asWidget() {
		if (container == null)
			createContainer();
		return container;
	}

	private void createContainer() {
		container = new FlowLayoutContainer();
		resultGrid = CalculatorResultGrid.getCalculatorGrid();
		label = new Label();

		container.add(label);
		container.add(resultGrid);
	}

	public void setResultData(CalculatorResultDataProxy resultData) {
		setLabelData(resultData);

		resultGrid.setRows(resultData.getRows());
		resultGrid.updateData();
	}

	private void setLabelData(CalculatorResultDataProxy resultData) {
		String text = "Дата старта работ " + DateUtils.getDateString(resultData.getStartDate()) + " дата окончания "
				+ DateUtils.getDateString(resultData.getFinishDate());
		label.setText(text);
	}

}
