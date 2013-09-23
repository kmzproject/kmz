package ru.kmz.server.data.generator;

import java.util.Date;

import ru.kmz.server.data.model.ProducteTemplate;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateGenerator {

	public static Template createTestTemplate() {
		Template template = new Template("Demo Template (" + new Date().toString() + ")");

		ProducteTemplate product = new ProducteTemplate("Изделие");
		template.setProduct(product);

		ProducteTemplateElement element1 = new ProducteTemplateElement("Ходовая часть", 3);
		product.add(element1);
		ProducteTemplateElement element1_1 = new ProducteTemplateElement("Вал", 19);
		element1.add(element1_1);
		element1_1.add(new ProducteTemplateElement("труба", 60));
		element1_1.add(new ProducteTemplateElement("цапфы", 7));
		element1_1.add(new ProducteTemplateElement("паковка V гр.", 55));
		element1_1.add(new ProducteTemplateElement("круг", 7));

		ProducteTemplateElement element1_2 = new ProducteTemplateElement("Корпуса", 12);
		element1.add(element1_2);
		element1_2.add(new ProducteTemplateElement("литые", 30));
		element1_2.add(new ProducteTemplateElement("SKF", 30));
		element1_2.add(new ProducteTemplateElement("подшипники", 21));

		ProducteTemplateElement element1_3 = new ProducteTemplateElement("Муфта", 6);
		element1.add(element1_3);
		element1_3.add(new ProducteTemplateElement("круг", 7));
		element1_3.add(new ProducteTemplateElement("лист", 7));

		ProducteTemplateElement element2 = new ProducteTemplateElement("Рабочее колесо", 10);
		product.add(element2);

		ProducteTemplateElement element2_1 = new ProducteTemplateElement("ступица", 2);
		element2.add(element2_1);
		element2_1.add(new ProducteTemplateElement("литье", 40));

		ProducteTemplateElement element2_2 = new ProducteTemplateElement("крыльчатка", 6);
		element2.add(element2_2);
		element2_2.add(new ProducteTemplateElement("лист", 7));

		ProducteTemplateElement element3 = new ProducteTemplateElement("Корпуса", 14);
		product.add(element3);

		ProducteTemplateElement element3_1 = new ProducteTemplateElement("Корпус", 7);
		element3.add(element3_1);
		element3_1.add(new ProducteTemplateElement("лист", 7));

		ProducteTemplateElement element3_2 = new ProducteTemplateElement("Карман", 3);
		element3.add(element3_2);
		element3_2.add(new ProducteTemplateElement("лист", 7));

		ProducteTemplateElement element3_3 = new ProducteTemplateElement("Конус", 2);
		element3.add(element3_3);
		element3_3.add(new ProducteTemplateElement("лист", 7));

		ProducteTemplateElement element3_4 = new ProducteTemplateElement("Рамы", 2);
		element3.add(element3_4);
		element3_4.add(new ProducteTemplateElement("лист", 7));

		ProducteTemplateElement element3_5 = new ProducteTemplateElement("Зип", 0);
		element3.add(element3_5);
		element3_5.add(new ProducteTemplateElement("метизы", 7));

		ProducteTemplateElement element4 = new ProducteTemplateElement("Н.А.", 10);
		product.add(element4);

		element4.add(new ProducteTemplateElement("лист", 7));
		element4.add(new ProducteTemplateElement("литье", 20));
		element4.add(new ProducteTemplateElement("оси", 7));

		return template;
	}
}
