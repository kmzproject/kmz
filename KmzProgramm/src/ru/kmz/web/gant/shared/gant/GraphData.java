package ru.kmz.web.gant.shared.gant;

import java.io.Serializable;
import java.util.Date;

import ru.kmz.web.common.shared.HasResourceType;

public abstract class GraphData implements Serializable, HasResourceType {
	private static final long serialVersionUID = 1L;

	public static final Date MINDATE = new Date(0);

	protected String name;
	protected Date planStart;
	protected Date planFinish;
	protected int duration;
	protected int complite;

	public GraphData() {
		planStart = MINDATE;
		planFinish = MINDATE;
		duration = 0;
		complite = 0;
	}

	public String getName() {
		return name;
	}

	public void setPlanStart(Date value) {
		planStart = value;
		if (planStart!=null){
			//TODO: вызрать обрезалку даты
		}
	}

	public Date getPlanStart() {
		return planStart;
	}

	public boolean isPlanStartNull() {
		return planStart.equals(MINDATE);
	}

	public void setPlanFinish(Date value) {
		planFinish = value;
	}

	public Date getPlanFinish() {
		return planFinish;
	}

	public boolean isPlanFinishNull() {
		return planFinish.equals(MINDATE);
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
}