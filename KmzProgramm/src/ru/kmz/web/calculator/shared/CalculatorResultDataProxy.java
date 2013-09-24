package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class CalculatorResultDataProxy implements Serializable {

	private List<CalculatorResultRowProxy> rows;
	private Date startDate;
	private Date finishDate;

	public CalculatorResultDataProxy() {
		rows = new ArrayList<CalculatorResultRowProxy>();
	}

	public void add(CalculatorResultRowProxy row) {
		if (startDate == null || startDate.after(row.getStartDate())) {
			startDate = row.getStartDate();
		}
		if (finishDate == null || finishDate.before(row.getFinishDate())) {
			finishDate = row.getFinishDate();
		}
		rows.add(row);
	}

	public List<CalculatorResultRowProxy> getRows() {
		return rows;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

}
