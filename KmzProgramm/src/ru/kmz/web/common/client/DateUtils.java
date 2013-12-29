package ru.kmz.web.common.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateUtils {

	public static String PATTERN = "yyyy.MM.dd";

	public static String PATTERN_MM_DD = "MM.dd";

	public static String getDateString(Date date) {
		return DateTimeFormat.getFormat(PATTERN).format(date);
	}
}
