package ru.kmz.web.ganttcommon.client;

import com.scheduler.client.core.timeaxis.preconfig.MonthTimeAxisGenerator;

public class QuaterTimeAxisGenerator extends MonthTimeAxisGenerator {

	public QuaterTimeAxisGenerator(String dateFormat) {
		super(dateFormat);
		intervall = 3;
	}
}
