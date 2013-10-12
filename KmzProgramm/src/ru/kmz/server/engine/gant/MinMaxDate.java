package ru.kmz.server.engine.gant;

import java.util.Date;

import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.engine.calculation.DateUtils;
import ru.kmz.server.engine.calculation.resources.ResourceTask;

public class MinMaxDate {
	private Date minDate;
	private Date maxDate;

	public MinMaxDate() {

	}

	public MinMaxDate(ResourceTask task) {
		this.minDate = task.getStart();
		this.maxDate = task.getFinish();
	}

	public MinMaxDate(ProductElementTask task) {
		this.minDate = new Date(task.getStart().getTime());
		this.maxDate = new Date(task.getFinish().getTime());
	}

	public void set(MinMaxDate date) {
		if (date == null || date.maxDate == null || date.minDate == null)
			return;

		if (minDate == null || minDate.after(date.minDate))
			minDate = date.minDate;

		if (maxDate == null || maxDate.before(date.maxDate))
			maxDate = date.maxDate;
	}

	public int getDuration() {
		return DateUtils.diffInDays(minDate, maxDate);
	}

	public Date getMinDate() {
		return minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

}
