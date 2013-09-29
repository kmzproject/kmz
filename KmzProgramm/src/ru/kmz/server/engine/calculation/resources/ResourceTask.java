package ru.kmz.server.engine.calculation.resources;

import java.util.Date;

import ru.kmz.server.engine.calculation.CalculationUtils;

public class ResourceTask {

	private Date start;
	private Date finish;
	private int duration;

	public ResourceTask(Date start, int duration) {
		this.start = start;
		this.duration = duration;
		this.finish = CalculationUtils.getOffsetDate(this.start, this.duration);
	}

	public Date getFinish() {
		return finish;
	}
	
	public Date getStart(){
		return start;
	}

}
