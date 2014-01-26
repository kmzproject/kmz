package ru.kmz.server.data.utils;

public class CacheUtils {

	public static void cleanAllCaches() {
		HistoryDataUtils.cleanCache();
		CalendarDataUtils.cleanCache();
		OrderDataUtils.cleanCache();
	}
}
