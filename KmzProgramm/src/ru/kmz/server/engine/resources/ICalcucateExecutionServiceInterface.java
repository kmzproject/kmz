package ru.kmz.server.engine.resources;

import java.util.Date;
import java.util.Map;

import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;

public interface ICalcucateExecutionServiceInterface {

	public void calculate(Template template, Date date);

	public Map<ProductTemplateElement, ResourceTask> getResult();
}
