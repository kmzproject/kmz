package ru.kmz.web.common.client.control;

import java.util.Date;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class DateCellFormat extends AbstractCell<Date> {

	private static DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy/MM/dd");

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Date value, SafeHtmlBuilder sb) {
		sb.appendHtmlConstant(dtf.format(value));
	}

}
