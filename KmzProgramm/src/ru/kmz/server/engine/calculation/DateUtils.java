package ru.kmz.server.engine.calculation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String FORMAT = "yyyy/MM/dd";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);

	public static Date getDate(String dateStr) {
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return null;
	}
}
