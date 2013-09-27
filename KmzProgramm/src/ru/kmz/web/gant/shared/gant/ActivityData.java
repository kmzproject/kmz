package ru.kmz.web.gant.shared.gant;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActivityData extends GraphData implements Serializable {

	private String resourceType;

	public ActivityData() {
		super();
	}

	public ActivityData(String resourceType) {
		this();
		this.resourceType = resourceType;
	}

	@Override
	public String getResourceType() {
		return resourceType;
	}
}
