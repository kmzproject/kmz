package ru.kmz.web.projects.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UpdateProjectElementTaskParams implements Serializable {

	private Date finishDate;
	private Date startDate;
	private int workDuration;

	public UpdateProjectElementTaskParams() {

	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(int workDuration) {
		this.workDuration = workDuration;
	}
}
