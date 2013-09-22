package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalculatorInputDataProxy implements Serializable {

	private Date finishDate;

	public void setFinishDate(Date valud) {
		this.finishDate = valud;
	}

	public Date getFinishDate() {
		return finishDate;
	}
}
