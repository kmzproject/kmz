package ru.kmz.web.common.shared.gant;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActivityData extends GraphData implements Serializable {

	public ActivityData() {
		super();
	}

	public ActivityData(String id, String name, int duration, String resourceType) {
		this();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.resourceType = resourceType;
	}
}
