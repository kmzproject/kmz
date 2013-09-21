package ru.kmz.web.calculator.client;

import java.util.Date;

import ru.kmz.web.calculator.shared.CalculatorResultRowProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CalculatorResultRowProxyProperties extends PropertyAccess<CalculatorResultRowProxy> {
	@Path("id")
	ModelKeyProvider<CalculatorResultRowProxy> key();

	@Path("name")
	LabelProvider<CalculatorResultRowProxy> nameLabel();

	ValueProvider<CalculatorResultRowProxy, String> name();

	ValueProvider<CalculatorResultRowProxy, Date> startDate();

	ValueProvider<CalculatorResultRowProxy, Date> finishDate();

}
