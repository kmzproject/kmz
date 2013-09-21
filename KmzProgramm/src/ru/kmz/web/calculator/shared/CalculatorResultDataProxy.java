package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CalculatorResultDataProxy implements Serializable {

	private List<CalculatorResultRowProxy> rows;

	public CalculatorResultDataProxy() {
		rows = new ArrayList<CalculatorResultRowProxy>();
	}

	public void add(CalculatorResultRowProxy row) {
		rows.add(row);
	}

	public List<CalculatorResultRowProxy> getRows() {
		return rows;
	}

}
