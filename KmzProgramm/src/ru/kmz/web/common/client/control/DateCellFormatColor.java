package ru.kmz.web.common.client.control;

import java.util.Date;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class DateCellFormatColor extends AbstractCell<Date> {

	private static DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy/MM/dd");
	private static long nowTime = new Date().getTime() / 1000;

	@Override
	public void render(Context context, Date value, SafeHtmlBuilder sb) {
		long valueTime = value.getTime() / 1000;
		String color;
		if (nowTime > valueTime) {
			color = "red";
		} else {
			color = "wight";
		}
		sb.appendHtmlConstant("<span style='color:" + color + ";'>" + dtf.format(value) + "</span>");
	}

}
