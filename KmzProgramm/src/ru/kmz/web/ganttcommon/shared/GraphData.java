package ru.kmz.web.ganttcommon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gantt.client.config.GanttConfig.TaskType;

import ru.kmz.web.common.shared.HasResourceType;
import ru.kmz.web.common.shared.ResourceTypesConsts;

@SuppressWarnings("serial")
public class GraphData implements Serializable, HasResourceType, IGraphDataContainer, Comparable<GraphData> {

	private String id;
	private String name;
	private Date planStart;
	private Date planFinish;
	private int duration;
	private int durationWork;
	private int complite;
	private String resourceType;
	private List<GraphData> childs;

	public GraphData() {
		childs = new ArrayList<GraphData>();
	}

	public GraphData(String id, String name, String code, int duration, int durationWork, String resourceType) {
		this();
		this.id = id;
		this.name = name;
		if (code!=null){
			this.name+=" ("+code+")";
		}
		this.duration = duration;
		this.durationWork = durationWork;
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

	public TaskType getTaskType() {
		if (isFolder()) {
			return TaskType.PARENT;
		}
		if (duration == 0) {
			return TaskType.MILESTONE;
		}
		return TaskType.LEAF;
	}

	public boolean isFolder() {
		return ResourceTypesConsts.isFolder(getResourceType());
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

	@Override
	public void addChild(GraphData child) {
		childs.add(child);
	}

	public List<GraphData> getChilds() {
		return childs;
	}

	@Override
	public int compareTo(GraphData ganttData) {
		return planStart.compareTo(ganttData.planStart);
	}

	public int getDurationWork() {
		return durationWork;
	}

	public void setDurationWork(int durationWork) {
		this.durationWork = durationWork;
	}

}
