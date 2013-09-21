package ru.kmz.web.calculator.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.datepicker.client.CalendarUtil;

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

	public void init() {
		add(new CalculatorResultRowProxy(1, "Ходовая часть", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "-вал", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--труба", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--цапфы", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--паковка V гр.", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--круг", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "-корпуса", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--литые", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--SKF", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--подшипники", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "-муфта", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--круг", getOffsetDate(-30), getOffsetDate(-20)));
		add(new CalculatorResultRowProxy(1, "--лист", getOffsetDate(-30), getOffsetDate(-20)));
	}

	private static Date finishDate = new Date();
	private static final long finishDateL = finishDate.getTime();

	private static Date getOffsetDate(int days) {
		final Date date = new Date(finishDateL);
		CalendarUtil.addDaysToDate(date, days);
		return date;
	}

}
