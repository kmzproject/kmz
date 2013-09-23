package ru.kmz.server.data.generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateGenerator {

	public static Template createTestTemplate() {
		Template template = new Template();

		ProducteTemplate product = new ProducteTemplate();
		template.setProduct(product);
		template.setName("templateName" + new Date().getTime());

		List<ProducteTemplateElement> elements = new ArrayList<ProducteTemplateElement>();

		ProducteTemplateElement element1 = new ProducteTemplateElement();
		element1.setName("element1");
		elements.add(element1);

		ProducteTemplateElement element2 = new ProducteTemplateElement();
		element2.setName("element2");
		elements.add(element2);

		ProducteTemplateElement element3 = new ProducteTemplateElement();
		element3.setName("element3");
		elements.add(element3);

		List<ProducteTemplateElement> elements3 = new ArrayList<ProducteTemplateElement>();
		ProducteTemplateElement element3_1 = new ProducteTemplateElement();
		element3_1.setName("element3_1");
		elements3.add(element3_1);
		element3.setChilds(elements3);

		product.setChilds(elements);

		return template;
	}
}
