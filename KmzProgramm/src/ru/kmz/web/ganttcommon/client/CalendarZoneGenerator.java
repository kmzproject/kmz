package ru.kmz.web.ganttcommon.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scheduler.client.zone.Zone;
import com.scheduler.client.zone.ZoneGeneratorInt;
import com.sencha.gxt.core.client.util.DateWrapper;

public class CalendarZoneGenerator implements ZoneGeneratorInt {

	private List<Date> records;

	public CalendarZoneGenerator(List<Date> records) {
		this.records = records;
	}

	public void setRecords(List<Date> records) {
		this.records = records;
	}

	@Override
	public List<Zone> generateZones(Date start, Date end) {
		ArrayList<Zone> zones = new ArrayList<Zone>();
		DateWrapper cursor = new DateWrapper(start);
		DateWrapper endDate = new DateWrapper(end);
		while (cursor.before(endDate)) {
			Date d = cursor.asDate();
			if (records.contains(d)) {
				Zone z = new Zone(d, cursor.addDays(1).asDate(), "zone-weekend ");
				zones.add(z);
			}
			cursor = cursor.addDays(1);
		}
		return zones;
	}
}
