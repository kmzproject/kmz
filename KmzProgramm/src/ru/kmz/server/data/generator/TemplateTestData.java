package ru.kmz.server.data.generator;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.ProductTemplateElement;
import ru.kmz.server.data.model.Template;
import ru.kmz.server.data.utils.TemplateDataUtils;

public class TemplateTestData {

	public static Template createTemplate1() {

		Template template = TemplateDataUtils.edit(new Template("Demo Template 1"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие", 0,
				ResourceTypes.FOLDER, template));

		ProductTemplateElement element1 = TemplateDataUtils.edit(new ProductTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));
		ProductTemplateElement element1_1 = TemplateDataUtils.edit(new ProductTemplateElement("Вал", 0,
				ResourceTypes.FOLDER, element1));

		ProductTemplateElement element1_1_1 = TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 1", 10,
				ResourceTypes.PREPARE, element1_1));
		TemplateDataUtils.edit(new ProductTemplateElement("Труба", 60, ResourceTypes.ORDER, element1_1_1));
		TemplateDataUtils.edit(new ProductTemplateElement("цапфы", 7, ResourceTypes.ORDER, element1_1_1));

		ProductTemplateElement element1_1_2 = TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 2", 5,
				ResourceTypes.PREPARE, element1_1));
		TemplateDataUtils.edit(new ProductTemplateElement("паковка V гр.", 55, ResourceTypes.ORDER, element1_1_2));

		ProductTemplateElement element1_1_3 = TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 3", 4,
				ResourceTypes.PREPARE, element1_1));
		TemplateDataUtils.edit(new ProductTemplateElement("круг", 7, ResourceTypes.ORDER, element1_1_3));

		ProductTemplateElement element1_2 = TemplateDataUtils.edit(new ProductTemplateElement("Корпуса", 12,
				ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProductTemplateElement("литые", 30, ResourceTypes.ORDER, element1_2));
		TemplateDataUtils.edit(new ProductTemplateElement("SKF", 30, ResourceTypes.ORDER, element1_2));
		TemplateDataUtils.edit(new ProductTemplateElement("подшипники", 21, ResourceTypes.ORDER, element1_2));

		ProductTemplateElement element1_3 = TemplateDataUtils.edit(new ProductTemplateElement("Муфта", 6,
				ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProductTemplateElement("круг", 7, ResourceTypes.ORDER, element1_3));
		TemplateDataUtils.edit(new ProductTemplateElement("лист", 7, ResourceTypes.ORDER, element1_3));

		ProductTemplateElement element2 = TemplateDataUtils.edit(new ProductTemplateElement("Рабочее колесо", 10,
				ResourceTypes.ASSEMBLAGE, root));

		ProductTemplateElement element2_1 = TemplateDataUtils.edit(new ProductTemplateElement("ступица", 2,
				ResourceTypes.PREPARE, element2));
		TemplateDataUtils.edit(new ProductTemplateElement("литье", 40, ResourceTypes.ORDER, element2_1));

		ProductTemplateElement element2_2 = TemplateDataUtils.edit(new ProductTemplateElement("крыльчатка", 6,
				ResourceTypes.PREPARE, element2));
		TemplateDataUtils.edit(new ProductTemplateElement("лист", 7, ResourceTypes.ORDER, element2_2));

		ProductTemplateElement element3 = TemplateDataUtils.edit(new ProductTemplateElement("Корпуса", 14,
				ResourceTypes.ASSEMBLAGE, root));

		ProductTemplateElement element3_1 = TemplateDataUtils.edit(new ProductTemplateElement("Корпус", 7,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProductTemplateElement("лист", 7, ResourceTypes.ORDER, element3_1));

		ProductTemplateElement element3_2 = TemplateDataUtils.edit(new ProductTemplateElement("Карман", 3,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProductTemplateElement("лист", 7, ResourceTypes.ORDER, element3_2));

		ProductTemplateElement element3_3 = TemplateDataUtils.edit(new ProductTemplateElement("Конус", 2,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProductTemplateElement("лист", 7, ResourceTypes.ORDER, element3_3));

		ProductTemplateElement element3_4 = TemplateDataUtils.edit(new ProductTemplateElement("Рамы", 2,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProductTemplateElement("лист", 7, ResourceTypes.ORDER, element3_4));

		ProductTemplateElement element3_5 = TemplateDataUtils.edit(new ProductTemplateElement("Зип", 0,
				ResourceTypes.PREPARE, element3));
		TemplateDataUtils.edit(new ProductTemplateElement("метизы", 7, ResourceTypes.ORDER, element3_5));

		ProductTemplateElement element4 = TemplateDataUtils.edit(new ProductTemplateElement("Н.А.", 10,
				ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Лист", 7, ResourceTypes.ORDER, element4));
		TemplateDataUtils.edit(new ProductTemplateElement("Литье", 20, ResourceTypes.ORDER, element4));
		TemplateDataUtils.edit(new ProductTemplateElement("Оси", 7, ResourceTypes.ORDER, element4));

		return template;
	}

	public static Template createTemplateShort2() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short2"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие Shoet2", 0,
				ResourceTypes.FOLDER, template));
		ProductTemplateElement element1 = TemplateDataUtils.edit(new ProductTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Вал", 0, ResourceTypes.ASSEMBLAGE, element1));

		return template;
	}

	public static Template createTemplateShort3() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short3"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие Short3", 0,
				ResourceTypes.FOLDER, template));
		ProductTemplateElement element1 = TemplateDataUtils.edit(new ProductTemplateElement("Ходовая часть", 3,
				ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 1", 10, ResourceTypes.PREPARE, element1));
		return template;
	}

	public static Template createTemplateShort4() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short4"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие Short3", 0,
				ResourceTypes.FOLDER, template));
		ProductTemplateElement element1 = TemplateDataUtils.edit(new ProductTemplateElement("Ходовая часть", 4,
				ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 1", 1, ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 2", 2, ResourceTypes.PREPARE, element1));
		TemplateDataUtils.edit(new ProductTemplateElement("Вал часть 3", 3, ResourceTypes.PREPARE, element1));
		return template;
	}

	public static Template createTemplateShort5() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short5"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие Short5", 0,
				ResourceTypes.FOLDER, template));

		TemplateDataUtils.edit(new ProductTemplateElement("Покупка 1", 1, ResourceTypes.ORDER, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Покупка 2", 2, ResourceTypes.ORDER, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Покупка 3", 3, ResourceTypes.ORDER, root));
		return template;
	}

	public static Template createTemplateShort6() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short6"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие Short6", 0,
				ResourceTypes.FOLDER, template));

		TemplateDataUtils.edit(new ProductTemplateElement("Сборка 1", 1, ResourceTypes.PREPARE, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Сборка 2", 2, ResourceTypes.PREPARE, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Сборка 3", 3, ResourceTypes.PREPARE, root));
		return template;
	}

	public static Template createTemplateShort7() {
		Template template = TemplateDataUtils.edit(new Template("Demo Template Short7"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие Short7", 0,
				ResourceTypes.FOLDER, template));

		ProductTemplateElement element1 = TemplateDataUtils.edit(new ProductTemplateElement("Сборка часть 1", 3,
				ResourceTypes.PREPARE, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Покупка 1", 3, ResourceTypes.ORDER, element1));

		ProductTemplateElement element2 = TemplateDataUtils.edit(new ProductTemplateElement("Сборка часть 2", 3,
				ResourceTypes.PREPARE, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Покупка 2", 3, ResourceTypes.ORDER, element2));

		ProductTemplateElement element3 = TemplateDataUtils.edit(new ProductTemplateElement("Сборка часть 3", 3,
				ResourceTypes.PREPARE, root));
		TemplateDataUtils.edit(new ProductTemplateElement("Покупка 3", 3, ResourceTypes.ORDER, element3));
		return template;
	}

	public static Template createTemplateShort8() {

		Template template = TemplateDataUtils.edit(new Template("Demo Template Short8"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие", 0,
				ResourceTypes.FOLDER, template));

		TemplateDataUtils.edit(new ProductTemplateElement("Ходовая часть", 14, ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Рабочее колесо", 7, ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Корпуса", 14, ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Направляющий аппарат", 14, ResourceTypes.ASSEMBLAGE, root));

		return template;
	}

	// TODO: постедний предоставленный шаблон
	public static Template createTemplateShort9() {

		Template template = TemplateDataUtils.edit(new Template("Demo Template Short9"));

		ProductTemplateElement root = TemplateDataUtils.edit(new ProductTemplateElement("Изделие", 0,
				ResourceTypes.FOLDER, template));

		TemplateDataUtils.edit(new ProductTemplateElement("Ходовая часть", 14, ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Рабочее колесо", 7, ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Корпуса", 14, ResourceTypes.ASSEMBLAGE, root));

		TemplateDataUtils.edit(new ProductTemplateElement("Направляющий аппарат", 14, ResourceTypes.ASSEMBLAGE, root));

		return template;
	}

	public static List<Template> createDemoTemplates() {
		List<Template> templates = new ArrayList<Template>(3);
		templates.add(TemplateTestData.createTemplate1());
		templates.add(TemplateTestData.createTemplateShort2());
		templates.add(TemplateTestData.createTemplateShort3());
		templates.add(TemplateTestData.createTemplateShort4());
		templates.add(TemplateTestData.createTemplateShort5());
		templates.add(TemplateTestData.createTemplateShort6());
		templates.add(TemplateTestData.createTemplateShort7());
		templates.add(TemplateTestData.createTemplateShort8());
		templates.add(TemplateTestData.createTemplateShort9());
		return templates;
	}
}
