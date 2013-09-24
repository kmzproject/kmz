package ru.kmz.server.engine.calculation;

import java.util.Calendar;
import java.util.Date;

public class CalculationUtils {
	private static final Calendar c = Calendar.getInstance();

	public static Date getOffsetDate(Date finish, int days) {
		c.setTime(finish);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

}
