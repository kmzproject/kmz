package ru.kmz.web.ganttcommon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class GanttData implements Serializable, IGraphDataContainer {

	private List<GraphData> childs;
	private List<Date> calendarRecords;
	private String name;
	private Date dateStart;
	private Date dateFinish;
	private String error;

	public GanttData() {
		childs = new ArrayList<GraphData>();
	}

	public GanttData(String name) {
		this();
		setName(name);
	}

	public void setName(String value) {
		name = value.replace("/", "_");
	}

	public String getName() {
		return name;
	}

	public List<GraphData> getChilds() {
		return childs;
	}

	public void setDateStart(Date value) {
		dateStart = value;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateFinish(Date value) {
		dateFinish = value;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	@Override
	public void addChild(GraphData child) {
		childs.add(child);
	}

	public String getError() {
		return error;
	}

	public static GanttData getError(String error) {
		GanttData gantt = new GanttData();
		gantt.error = error;
		return gantt;
	}

	public List<Date> getCalendarRecords() {
		return calendarRecords;
	}

	public void setCalendarRecords(List<Date> records) {
		this.calendarRecords = records;
	}
}
