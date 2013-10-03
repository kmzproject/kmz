package ru.kmz.server.data.generator;

import java.util.Date;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;

public class TemplateGenerator {

	public static Template createTestTemplate() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template (" + new Date().toString() + ")"));

		ProducteTemplateElement root = TemplateDataUtils.edit(new ProducteTemplateElement("Изделие", 0,
				ResourceTypes.FOLDER));
		template.setRootElement(root);

		ProducteTemplateElement element1 = TemplateDataUtils.edit(new ProducteTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE));
		root.add(element1);
		ProducteTemplateElement element1_1 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал", 0,
				ResourceTypes.ASSEMBLAGE));
		element1.add(element1_1);

		ProducteTemplateElement element1_1_1 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 1", 10,
				ResourceTypes.PREPARE));
		element1_1.add(element1_1_1);
		element1_1_1.add(TemplateDataUtils.edit(new ProducteTemplateElement("труба", 60, ResourceTypes.ORDER)));
		element1_1_1.add(TemplateDataUtils.edit(new ProducteTemplateElement("цапфы", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element1_1_1);

		ProducteTemplateElement element1_1_2 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 2", 5,
				ResourceTypes.PREPARE));
		element1_1.add(element1_1_2);
		element1_1_2.add(TemplateDataUtils.edit(new ProducteTemplateElement("паковка V гр.", 55, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element1_1_2);

		ProducteTemplateElement element1_1_3 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 3", 4,
				ResourceTypes.PREPARE));
		element1_1.add(element1_1_3);
		element1_1_3.add(TemplateDataUtils.edit(new ProducteTemplateElement("круг", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element1_1_3);
		TemplateDataUtils.edit(element1_1);

		ProducteTemplateElement element1_2 = TemplateDataUtils.edit(new ProducteTemplateElement("Корпуса", 12,
				ResourceTypes.PREPARE));
		element1.add(element1_2);
		element1_2.add(TemplateDataUtils.edit(new ProducteTemplateElement("литые", 30, ResourceTypes.ORDER)));
		element1_2.add(TemplateDataUtils.edit(new ProducteTemplateElement("SKF", 30, ResourceTypes.ORDER)));
		element1_2.add(TemplateDataUtils.edit(new ProducteTemplateElement("подшипники", 21, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element1_2);

		ProducteTemplateElement element1_3 = TemplateDataUtils.edit(new ProducteTemplateElement("Муфта", 6,
				ResourceTypes.PREPARE));
		element1.add(element1_3);
		element1_3.add(TemplateDataUtils.edit(new ProducteTemplateElement("круг", 7, ResourceTypes.ORDER)));
		element1_3.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element1_3);
		TemplateDataUtils.edit(element1);

		ProducteTemplateElement element2 = TemplateDataUtils.edit(new ProducteTemplateElement("Рабочее колесо", 10,
				ResourceTypes.ASSEMBLAGE));
		root.add(element2);

		ProducteTemplateElement element2_1 = TemplateDataUtils.edit(new ProducteTemplateElement("ступица", 2,
				ResourceTypes.PREPARE));
		element2.add(element2_1);
		element2_1.add(TemplateDataUtils.edit(new ProducteTemplateElement("литье", 40, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element2_1);

		ProducteTemplateElement element2_2 = TemplateDataUtils.edit(new ProducteTemplateElement("крыльчатка", 6,
				ResourceTypes.PREPARE));
		element2.add(element2_2);
		element2_2.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element2_2);
		TemplateDataUtils.edit(element2);

		ProducteTemplateElement element3 = TemplateDataUtils.edit(new ProducteTemplateElement("Корпуса", 14,
				ResourceTypes.ASSEMBLAGE));
		root.add(element3);

		ProducteTemplateElement element3_1 = TemplateDataUtils.edit(new ProducteTemplateElement("Корпус", 7,
				ResourceTypes.PREPARE));
		element3.add(element3_1);
		element3_1.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element3_1);

		ProducteTemplateElement element3_2 = TemplateDataUtils.edit(new ProducteTemplateElement("Карман", 3,
				ResourceTypes.PREPARE));
		element3.add(element3_2);
		element3_2.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element3_2);

		ProducteTemplateElement element3_3 = TemplateDataUtils.edit(new ProducteTemplateElement("Конус", 2,
				ResourceTypes.PREPARE));
		element3.add(element3_3);
		element3_3.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element3_3);

		ProducteTemplateElement element3_4 = TemplateDataUtils.edit(new ProducteTemplateElement("Рамы", 2,
				ResourceTypes.PREPARE));
		element3.add(element3_4);
		element3_4.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element3_4);

		ProducteTemplateElement element3_5 = TemplateDataUtils.edit(new ProducteTemplateElement("Зип", 0,
				ResourceTypes.PREPARE));
		element3.add(element3_5);
		element3_5.add(TemplateDataUtils.edit(new ProducteTemplateElement("метизы", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element3_5);
		TemplateDataUtils.edit(element3);

		ProducteTemplateElement element4 = TemplateDataUtils.edit(new ProducteTemplateElement("Н.А.", 10,
				ResourceTypes.ASSEMBLAGE));
		root.add(element4);

		element4.add(TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER)));
		element4.add(TemplateDataUtils.edit(new ProducteTemplateElement("литье", 20, ResourceTypes.ORDER)));
		element4.add(TemplateDataUtils.edit(new ProducteTemplateElement("оси", 7, ResourceTypes.ORDER)));
		TemplateDataUtils.edit(element4);

		return template;
	}
}
