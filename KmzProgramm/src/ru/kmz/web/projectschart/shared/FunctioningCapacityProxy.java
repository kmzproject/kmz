package ru.kmz.web.projectschart.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FunctioningCapacityProxy implements Serializable {

	private String day;
	private int activitiesCount;

	public FunctioningCapacityProxy() {

	}

	public FunctioningCapacityProxy(String day, int activitiesCount) {
		this.day = day;
		this.activitiesCount = activitiesCount;
	}

	public String getDay() {
		return day;
	}

	public int getActivitiesCount() {
		return activitiesCount;
	}

}
