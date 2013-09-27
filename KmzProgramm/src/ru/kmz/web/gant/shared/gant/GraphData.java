package ru.kmz.web.gant.shared.gant;

import java.io.Serializable;
import java.util.Date;

import ru.kmz.web.common.shared.HasResourceType;

@SuppressWarnings("serial")
public abstract class GraphData implements Serializable, HasResourceType {

	protected String id;
	protected String name;
	protected Date planStart;
	protected Date planFinish;
	protected int duration;
	protected int complite;
	protected String resourceType;

	public GraphData() {

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
}
