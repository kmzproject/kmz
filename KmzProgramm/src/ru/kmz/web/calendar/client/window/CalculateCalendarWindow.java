package ru.kmz.web.calendar.client.window;

import ru.kmz.web.calendar.shared.CalculateCalendarParamProxy;
import ru.kmz.web.common.client.window.CommonSelectWindow;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class CalculateCalendarWindow extends CommonSelectWindow<CalculateCalendarParamProxy> {

	private DateField dateFrom;
	private DateField dateTo;

	public CalculateCalendarWindow() {
		super();
		setPixelSize(500, 50);
		setHeadingText("Расчет выходных");
	}

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();

		dateFrom = new DateField();
		dateTo = new DateField();

		p.add(new FieldLabel(dateFrom, "С"), new VerticalLayoutData(1, -1));
		p.add(new FieldLabel(dateTo, "По"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	protected CalculateCalendarParamProxy getSelectedValue() {
		CalculateCalendarParamProxy proxy = new CalculateCalendarParamProxy(dateFrom.getValue(), dateTo.getValue());
		return proxy;
	}

}
