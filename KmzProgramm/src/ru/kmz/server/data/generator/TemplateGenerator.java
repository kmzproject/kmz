package ru.kmz.server.data.generator;

import java.util.Date;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateGenerator {

	public static Template createTestTemplate() {
		Template template = new Template("Demo Template (" + new Date().toString() + ")");

		ProducteTemplate product = new ProducteTemplate("Изделие");
		template.setProduct(product);

		ProducteTemplateElement element1 = new ProducteTemplateElement("Ходовая часть", 3, ResourceTypes.ASSEMBLAGE);
		product.add(element1);
		ProducteTemplateElement element1_1 = new ProducteTemplateElement("Вал", 0, ResourceTypes.PREPARE);
		element1.add(element1_1);

		ProducteTemplateElement element1_1_1 = new ProducteTemplateElement("Вал часть 1", 10, ResourceTypes.PREPARE);
		element1_1.add(element1_1_1);
		element1_1_1.add(new ProducteTemplateElement("труба", 60, ResourceTypes.ORDER));
		element1_1_1.add(new ProducteTemplateElement("цапфы", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element1_1_2 = new ProducteTemplateElement("Вал часть 2", 5, ResourceTypes.PREPARE);
		element1_1.add(element1_1_2);
		element1_1_2.add(new ProducteTemplateElement("паковка V гр.", 55, ResourceTypes.ORDER));

		ProducteTemplateElement element1_1_3 = new ProducteTemplateElement("Вал часть 3", 4, ResourceTypes.PREPARE);
		element1_1.add(element1_1_3);
		element1_1_3.add(new ProducteTemplateElement("круг", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element1_2 = new ProducteTemplateElement("Корпуса", 12, ResourceTypes.PREPARE);
		element1.add(element1_2);
		element1_2.add(new ProducteTemplateElement("литые", 30, ResourceTypes.ORDER));
		element1_2.add(new ProducteTemplateElement("SKF", 30, ResourceTypes.ORDER));
		element1_2.add(new ProducteTemplateElement("подшипники", 21, ResourceTypes.ORDER));

		ProducteTemplateElement element1_3 = new ProducteTemplateElement("Муфта", 6, ResourceTypes.PREPARE);
		element1.add(element1_3);
		element1_3.add(new ProducteTemplateElement("круг", 7, ResourceTypes.ORDER));
		element1_3.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element2 = new ProducteTemplateElement("Рабочее колесо", 10, ResourceTypes.ASSEMBLAGE);
		product.add(element2);

		ProducteTemplateElement element2_1 = new ProducteTemplateElement("ступица", 2, ResourceTypes.PREPARE);
		element2.add(element2_1);
		element2_1.add(new ProducteTemplateElement("литье", 40, ResourceTypes.ORDER));

		ProducteTemplateElement element2_2 = new ProducteTemplateElement("крыльчатка", 6, ResourceTypes.PREPARE);
		element2.add(element2_2);
		element2_2.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element3 = new ProducteTemplateElement("Корпуса", 14, ResourceTypes.ASSEMBLAGE);
		product.add(element3);

		ProducteTemplateElement element3_1 = new ProducteTemplateElement("Корпус", 7, ResourceTypes.PREPARE);
		element3.add(element3_1);
		element3_1.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element3_2 = new ProducteTemplateElement("Карман", 3, ResourceTypes.PREPARE);
		element3.add(element3_2);
		element3_2.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element3_3 = new ProducteTemplateElement("Конус", 2, ResourceTypes.PREPARE);
		element3.add(element3_3);
		element3_3.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element3_4 = new ProducteTemplateElement("Рамы", 2, ResourceTypes.PREPARE);
		element3.add(element3_4);
		element3_4.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element3_5 = new ProducteTemplateElement("Зип", 0, ResourceTypes.PREPARE);
		element3.add(element3_5);
		element3_5.add(new ProducteTemplateElement("метизы", 7, ResourceTypes.ORDER));

		ProducteTemplateElement element4 = new ProducteTemplateElement("Н.А.", 10, ResourceTypes.ASSEMBLAGE);
		product.add(element4);

		element4.add(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER));
		element4.add(new ProducteTemplateElement("литье", 20, ResourceTypes.ORDER));
		element4.add(new ProducteTemplateElement("оси", 7, ResourceTypes.ORDER));

		return template;
	}
}
