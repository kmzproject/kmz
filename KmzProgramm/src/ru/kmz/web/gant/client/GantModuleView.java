package ru.kmz.web.gant.client;

import java.util.Date;

import ru.kmz.web.gant.shared.GanttData;
import ru.kmz.web.gant.shared.ScaleConstants;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.DateWrapper;

public class GantModuleView implements EntryPoint, IsWidget {

	@Override
	public void onModuleLoad() {
		if (RootPanel.get("gant") != null)
			RootPanel.get("gant").add(asWidget());
	}

	@Override
	public Widget asWidget() {
		GanttData data = new GanttData();
		data.setName("Test");
		data.setScale(ScaleConstants.DAY);
		DateWrapper dw = new DateWrapper(new Date()).clearTime().addDays(-7);
		data.setDateStart(dw.addDays(-20).asDate());
		data.setDateFinish(dw.addDays(20).asDate());
		CalculationTemplateGant gant = new CalculationTemplateGant(data);
		return gant.asWidget();
	}

}
