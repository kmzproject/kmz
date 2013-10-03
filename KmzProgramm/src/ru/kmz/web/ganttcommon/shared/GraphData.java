package ru.kmz.web.ganttcommon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.web.common.shared.HasResourceType;

@SuppressWarnings("serial")
public class GraphData implements Serializable, HasResourceType {

	private String id;
	private String name;
	private Date planStart;
	private Date planFinish;
	private int duration;
	private int complite;
	private String resourceType;
	private List<GraphData> childs;

	public GraphData() {
		childs = new ArrayList<GraphData>();
	}

	public GraphData(String id, String name, int duration, String resourceType) {
		this();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.resourceType = resourceType;
	}

	public String getName() {
		return name;
	}

	public void setPlanStart(Date value) {
		planStart = value;
	}

	public Date getPlanStart() {
		return planStart;
	}

	public void setPlanFinish(Date value) {
		planFinish = value;
	}

	public Date getPlanFinish() {
		return planFinish;
	}

	public void setDuration(int value) {
		duration = value;
	}

	public int getDuration() {
		return duration;
	}

	public void setName(String name) {
		this.name = name.replace("\"", "_");
	}

	public boolean isMilestone() {
		return duration == 0;
	}

	public boolean isFolder() {
		return getResourceType() == null;
	}

	public int getComplite() {
		return complite;
	}

	public void setComplite(int value) {
		complite = value;
	}

	public String getId() {
		return id;
	}

	@Override
	public String getResourceType() {
		return resourceType;
	}

	public void addChild(GraphData child) {
		childs.add(child);
	}

	public List<GraphData> getChilds() {
		return childs;
	}

}
