package ru.kmz.web.ganttcommon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class GanttData implements Serializable {

	private List<WbsData> wbss;
	private String name;
	private Date dateStart;
	private Date dateFinish;
	private String scale;

	public GanttData() {
		wbss = new ArrayList<WbsData>();
		scale = ScaleConstants.WEEK;
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

	public List<WbsData> getWbss() {
		return wbss;
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

	public void add(WbsData wbs) {
		wbss.add(wbs);
	}
}
