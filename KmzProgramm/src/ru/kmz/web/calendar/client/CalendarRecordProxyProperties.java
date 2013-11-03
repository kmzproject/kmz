package ru.kmz.web.calendar.client;

import java.util.Date;

import ru.kmz.web.calendar.shared.CalendarRecordProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CalendarRecordProxyProperties extends PropertyAccess<CalendarRecordProxy> {
	@Path("id")
	ModelKeyProvider<CalendarRecordProxy> key();

	@Path("date")
	ValueProvider<CalendarRecordProxy, Date> date();

	ValueProvider<CalendarRecordProxy, String> comment();

}