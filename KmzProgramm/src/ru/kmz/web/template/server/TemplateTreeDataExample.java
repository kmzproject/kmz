package ru.kmz.web.template.server;

import ru.kmz.web.template.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.template.shared.TemplateTreeNodeFolderProxy;

public class TemplateTreeDataExample {

	public static TemplateTreeNodeFolderProxy getRoot() {
		TemplateTreeNodeFolderProxy root = new TemplateTreeNodeFolderProxy("root", "Root");
		root.add(getFolder1());
		root.add(getFolder2());
		return root;
	}

	private static TemplateTreeNodeFolderProxy getFolder1() {
		TemplateTreeNodeFolderProxy folder1 = new TemplateTreeNodeFolderProxy("folder1", "Ходовая часть");

		TemplateTreeNodeFolderProxy folder1_1 = new TemplateTreeNodeFolderProxy("folder1_1", "вал");
		TemplateTreeNodeFolderProxy folder1_2 = new TemplateTreeNodeFolderProxy("folder1_2", "корпуса");
		TemplateTreeNodeFolderProxy folder1_3 = new TemplateTreeNodeFolderProxy("folder1_3", "муфта");

		folder1.add(folder1_1);
		folder1.add(folder1_2);
		folder1.add(folder1_3);

		folder1_1.add(new TemplateTreeNodeBaseProxy("folder1_1_1", "труба"));
		folder1_1.add(new TemplateTreeNodeBaseProxy("folder1_1_2", "цапфы"));
		folder1_1.add(new TemplateTreeNodeBaseProxy("folder1_1_3", "паковка V гр."));
		folder1_1.add(new TemplateTreeNodeBaseProxy("folder1_1_4", "круг"));

		folder1_2.add(new TemplateTreeNodeBaseProxy("folder1_2_1", "литые"));
		folder1_2.add(new TemplateTreeNodeBaseProxy("folder1_2_2", "SKF"));
		folder1_2.add(new TemplateTreeNodeBaseProxy("folder1_2_3", "подшипники"));

		folder1_3.add(new TemplateTreeNodeBaseProxy("folder1_3_1", "круг"));
		folder1_3.add(new TemplateTreeNodeBaseProxy("folder1_3_2", "лист"));

		return folder1;
	}

	private static TemplateTreeNodeFolderProxy getFolder2() {
		TemplateTreeNodeFolderProxy folder1 = new TemplateTreeNodeFolderProxy("folder2", "р.к.");

		TemplateTreeNodeFolderProxy folder1_1 = new TemplateTreeNodeFolderProxy("folder2_1", "ступица");
		TemplateTreeNodeFolderProxy folder1_2 = new TemplateTreeNodeFolderProxy("folder2_2", "крыльчатка");

		folder1.add(folder1_1);
		folder1.add(folder1_2);

		folder1_1.add(new TemplateTreeNodeBaseProxy("folder2_1_1", "литье"));

		folder1_2.add(new TemplateTreeNodeBaseProxy("folder2_2_1", "лист"));

		return folder1;
	}
}
