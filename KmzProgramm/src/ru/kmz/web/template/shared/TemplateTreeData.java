package ru.kmz.web.template.shared;


public class TemplateTreeData {

	private TemplateTreeNodeFolder root;

	public TemplateTreeNodeFolder getTreeRoot() {
		return root;
	}

	public TemplateTreeData() {
		initTestData();
	}

	private void initTestData() {
		root = new TemplateTreeNodeFolder("root", "Root");
		root.add(getFolder1());
		root.add(getFolder2());
	}

	private static TemplateTreeNodeFolder getFolder1() {
		TemplateTreeNodeFolder folder1 = new TemplateTreeNodeFolder("folder1", "Ходовая часть");

		TemplateTreeNodeFolder folder1_1 = new TemplateTreeNodeFolder("folder1_1", "вал");
		TemplateTreeNodeFolder folder1_2 = new TemplateTreeNodeFolder("folder1_2", "корпуса");
		TemplateTreeNodeFolder folder1_3 = new TemplateTreeNodeFolder("folder1_3", "муфта");

		folder1.add(folder1_1);
		folder1.add(folder1_2);
		folder1.add(folder1_3);

		folder1_1.add(new TemplateTreeNodeBase("folder1_1_1", "труба"));
		folder1_1.add(new TemplateTreeNodeBase("folder1_1_2", "цапфы"));
		folder1_1.add(new TemplateTreeNodeBase("folder1_1_3", "паковка V гр."));
		folder1_1.add(new TemplateTreeNodeBase("folder1_1_4", "круг"));

		folder1_2.add(new TemplateTreeNodeBase("folder1_2_1", "литые"));
		folder1_2.add(new TemplateTreeNodeBase("folder1_2_2", "SKF"));
		folder1_2.add(new TemplateTreeNodeBase("folder1_2_3", "подшипники"));

		folder1_3.add(new TemplateTreeNodeBase("folder1_3_1", "круг"));
		folder1_3.add(new TemplateTreeNodeBase("folder1_3_2", "лист"));

		return folder1;
	}

	private static TemplateTreeNodeFolder getFolder2() {
		TemplateTreeNodeFolder folder1 = new TemplateTreeNodeFolder("folder2", "р.к.");

		TemplateTreeNodeFolder folder1_1 = new TemplateTreeNodeFolder("folder2_1", "ступица");
		TemplateTreeNodeFolder folder1_2 = new TemplateTreeNodeFolder("folder2_2", "крыльчатка");

		folder1.add(folder1_1);
		folder1.add(folder1_2);

		folder1_1.add(new TemplateTreeNodeBase("folder2_1_1", "литье"));

		folder1_2.add(new TemplateTreeNodeBase("folder2_2_1", "лист"));

		return folder1;
	}

}
