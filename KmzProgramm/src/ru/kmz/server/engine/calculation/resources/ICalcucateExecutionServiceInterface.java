package ru.kmz.server.engine.calculation.resources;

import java.util.Date;
import java.util.Map;

import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public interface ICalcucateExecutionServiceInterface {

	public void calculate(Template template, Date date);

	public Map<ProducteTemplateElement, ResourceTask> getResult();
}
