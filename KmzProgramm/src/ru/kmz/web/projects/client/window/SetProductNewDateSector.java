package ru.kmz.web.projects.client.window;

import java.util.Date;

import ru.kmz.web.common.client.window.CommonSelectWindow;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class SetProductNewDateSector extends CommonSelectWindow<Date> {

	private DateField dateField;

	@Override
	protected Container getInfoContainer() {
		final VerticalLayoutContainer p = new VerticalLayoutContainer();

		dateField = new DateField();

		p.add(new FieldLabel(dateField, "Дата"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	protected Date getSelectedValue() {
		return dateField.getValue();
	}
}
