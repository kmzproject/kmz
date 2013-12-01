package ru.kmz.server.engine.projects;

import java.util.Date;

import ru.kmz.server.utils.DateUtils;

public class OffsetService {

	public Date getOffsetDate(Date date, int days) {
		return DateUtils.getOffsetDate(date, days);
	}
}
