package ru.kmz.web.gant.shared.gant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class WbsData extends GraphData implements Serializable {

	private List<ActivityData> activities;
	private List<WbsData> childs;

	public WbsData() {
		activities = new ArrayList<ActivityData>();
		childs = new ArrayList<WbsData>();
	}

	public WbsData(String id, String name, int duration, String resourceType) {
		this();
		this.id = id;
		this.name = name;
		this.resourceType = resourceType;
		this.duration = duration;
	}

	public void addActivity(ActivityData acticity) {
		activities.add(acticity);
	}

	public void addChild(WbsData wbs) {
		childs.add(wbs);
	}

	public List<WbsData> getChilds() {
		return childs;
	}

	public List<ActivityData> getActivities() {
		return activities;
	}
}
