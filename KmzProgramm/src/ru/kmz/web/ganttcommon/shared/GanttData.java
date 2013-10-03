package ru.kmz.web.ganttcommon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class GanttData implements Serializable {

	private List<GraphData> childs;
	private String name;
	private Date dateStart;
	private Date dateFinish;
	private String scale;
	private String error;

	public GanttData() {
		childs = new ArrayList<GraphData>();
	}

	public GanttData(String name) {
		this();
		setName(name);
	}

	private void setName(String value) {
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

	public String getScale() {
		return scale;
	}

	public void add(GraphData wbs) {
		childs.add(wbs);
	}

	public String getError() {
		return error;
	}

	public static GanttData getError(String error) {
		GanttData gantt = new GanttData();
		gantt.error = error;
		return gantt;
	}

	public void setScale(String value) {
		this.scale = value;
	}
}
