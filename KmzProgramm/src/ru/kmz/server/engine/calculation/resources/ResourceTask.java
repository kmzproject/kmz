package ru.kmz.server.engine.calculation.resources;

import java.util.Date;

import ru.kmz.server.data.model.Resource;
import ru.kmz.server.engine.calculation.CalculationUtils;

public class ResourceTask implements Comparable<ResourceTask> {

	private Date start;
	private Date finish;
	private int duration;
	private Resource resource;

	public ResourceTask(Resource resource, Date start, int duration) {
		this(start, duration);
		this.resource = resource;
	}

	public ResourceTask(Date start, Date finish, int duration) {
		this.start = start;
		this.duration = duration;
		this.finish = finish;
	}

	public ResourceTask(Date start, int duration) {
		this.start = start;
		this.duration = duration;
		this.finish = CalculationUtils.getOffsetDate(this.start, this.duration);
	}

	public Date getFinish() {
		return finish;
	}

	public Date getStart() {
		return start;
	}

	@Override
	public String toString() {
		return start.toString() + " " + duration + " " + finish.toString();
	}

	@Override
	public int compareTo(ResourceTask task) {
		if (start.before(task.start))
			return -1;
		if (start.equals(task.start))
			return 0;
		return 1;
	}

	public Resource getResource() {
		return resource;
	}

	public void toFinish(Date finish) {
		this.finish = finish;
		this.start = CalculationUtils.getOffsetDate(this.finish, -this.duration);
	}
}
