package ru.kmz.web.calculator.server;

import java.util.Calendar;
import java.util.Date;

import ru.kmz.web.calculator.shared.CalculatorResultDataProxy;
import ru.kmz.web.calculator.shared.CalculatorResultRowProxy;

public class CalculatorResultDataExample {

	public static CalculatorResultDataProxy getExample() {
		CalculatorResultDataProxy data = new CalculatorResultDataProxy();
		data.add(new CalculatorResultRowProxy(1, "Ходовая часть", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "-вал", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--труба", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--цапфы", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--паковка V гр.", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--круг", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "-корпуса", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--литые", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--SKF", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--подшипники", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "-муфта", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--круг", getOffsetDate(-30), getOffsetDate(-20)));
		data.add(new CalculatorResultRowProxy(1, "--лист", getOffsetDate(-30), getOffsetDate(-20)));

		return data;
	}

	private static Date finishDate = new Date();
	private static final Calendar c = Calendar.getInstance();

	private static Date getOffsetDate(int days) {
		c.setTime(finishDate);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

}
