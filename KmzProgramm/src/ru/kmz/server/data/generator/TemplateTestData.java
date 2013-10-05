package ru.kmz.server.data.generator;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;

public class TemplateTestData {

	public static Template createTemplate() {

		Template template = TemplateDataUtils.edit(new Template("Demo Template1"));

		ProducteTemplateElement root = TemplateDataUtils.edit(new ProducteTemplateElement("Изделие", 0,
				ResourceTypes.FOLDER, template));

		ProducteTemplateElement element1 = TemplateDataUtils.edit(new ProducteTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));
		ProducteTemplateElement element1_1 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал", 0,
				ResourceTypes.FOLDER, element1));

		ProducteTemplateElement element1_1_1 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 1", 10,
				ResourceTypes.PREPARE, element1_1));
		TemplateDataUtils.edit(new ProducteTemplateElement("Труба", 60, ResourceTypes.ORDER, element1_1_1));
		TemplateDataUtils.edit(new ProducteTemplateElement("цапфы", 7, ResourceTypes.ORDER, element1_1_1));

		ProducteTemplateElement element1_1_2 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 2", 5,
				ResourceTypes.PREPARE, element1_1));
		TemplateDataUtils.edit(new ProducteTemplateElement("паковка V гр.", 55, ResourceTypes.ORDER, element1_1_2));

		ProducteTemplateElement element1_1_3 = TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 3", 4,
				ResourceTypes.PREPARE, element1_1));
		TemplateDataUtils.edit(new ProducteTemplateElement("круг", 7, ResourceTypes.ORDER, element1_1_3));

		ProducteTemplateElement element1_2 = TemplateDataUtils.edit(new ProducteTemplateElement("Корпуса", 12,
				ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProducteTemplateElement("литые", 30, ResourceTypes.ORDER, element1_2));
		TemplateDataUtils.edit(new ProducteTemplateElement("SKF", 30, ResourceTypes.ORDER, element1_2));
		TemplateDataUtils.edit(new ProducteTemplateElement("подшипники", 21, ResourceTypes.ORDER, element1_2));

		ProducteTemplateElement element1_3 = TemplateDataUtils.edit(new ProducteTemplateElement("Муфта", 6,
				ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProducteTemplateElement("круг", 7, ResourceTypes.ORDER, element1_3));
		TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER, element1_3));

		ProducteTemplateElement element2 = TemplateDataUtils.edit(new ProducteTemplateElement("Рабочее колесо", 10,
				ResourceTypes.ASSEMBLAGE, root));

		ProducteTemplateElement element2_1 = TemplateDataUtils.edit(new ProducteTemplateElement("ступица", 2,
				ResourceTypes.PREPARE, element2));
		TemplateDataUtils.edit(new ProducteTemplateElement("литье", 40, ResourceTypes.ORDER, element2_1));

		ProducteTemplateElement element2_2 = TemplateDataUtils.edit(new ProducteTemplateElement("крыльчатка", 6,
				ResourceTypes.PREPARE, element2));
		TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER, element2_2));

		ProducteTemplateElement element3 = TemplateDataUtils.edit(new ProducteTemplateElement("Корпуса", 14,
				ResourceTypes.ASSEMBLAGE, root));

		ProducteTemplateElement element3_1 = TemplateDataUtils.edit(new ProducteTemplateElement("Корпус", 7,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER, element3_1));

		ProducteTemplateElement element3_2 = TemplateDataUtils.edit(new ProducteTemplateElement("Карман", 3,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER, element3_2));

		ProducteTemplateElement element3_3 = TemplateDataUtils.edit(new ProducteTemplateElement("Конус", 2,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER, element3_3));

		ProducteTemplateElement element3_4 = TemplateDataUtils.edit(new ProducteTemplateElement("Рамы", 2,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProducteTemplateElement("лист", 7, ResourceTypes.ORDER, element3_4));

		ProducteTemplateElement element3_5 = TemplateDataUtils.edit(new ProducteTemplateElement("Зип", 0,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProducteTemplateElement("метизы", 7, ResourceTypes.ORDER, element3_5));

		ProducteTemplateElement element4 = TemplateDataUtils.edit(new ProducteTemplateElement("Н.А.", 10,
				ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProducteTemplateElement("Лист", 7, ResourceTypes.ORDER, element4));
		TemplateDataUtils.edit(new ProducteTemplateElement("Литье", 20, ResourceTypes.ORDER, element4));
		TemplateDataUtils.edit(new ProducteTemplateElement("Оси", 7, ResourceTypes.ORDER, element4));

		return template;
	}

	public static Template createTemplateShort2() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short2"));

		ProducteTemplateElement root = TemplateDataUtils.edit(new ProducteTemplateElement("Изделие Shoet2", 0,
				ResourceTypes.FOLDER, template));
		ProducteTemplateElement element1 = TemplateDataUtils.edit(new ProducteTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));
		TemplateDataUtils.edit(new ProducteTemplateElement("Вал", 0, ResourceTypes.ASSEMBLAGE, element1));

		return template;
	}

	public static Template createTemplateShort3() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short3"));

		ProducteTemplateElement root = TemplateDataUtils.edit(new ProducteTemplateElement("Изделие Shoet3", 0,
				ResourceTypes.FOLDER, template));
		ProducteTemplateElement element1 = TemplateDataUtils.edit(new ProducteTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 1", 10, ResourceTypes.PREPARE, element1));
		return template;
	}

	public static Template createTemplateShort4() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short4"));

		ProducteTemplateElement root = TemplateDataUtils.edit(new ProducteTemplateElement("Изделие Shoet3", 0,
				ResourceTypes.FOLDER, template));
		ProducteTemplateElement element1 = TemplateDataUtils.edit(new ProducteTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 1", 10, ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 2", 5, ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProducteTemplateElement("Вал часть 3", 4, ResourceTypes.PREPARE, element1));
		return template;
	}

	public static List<Template> getDempTemplates() {
		List<Template> templates = new ArrayList<Template>(3);
		templates.add(TemplateTestData.createTemplate());
		templates.add(TemplateTestData.createTemplateShort2());
		templates.add(TemplateTestData.createTemplateShort3());
		templates.add(TemplateTestData.createTemplateShort4());
		return templates;
	}
}
