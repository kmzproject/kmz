package ru.kmz.web.projectscommon.shared;

import java.util.Date;

@SuppressWarnings("serial")
public class ProductionProxy extends ProductElementTaskProxy {

	public ProductionProxy() {
		super();
	}

	public ProductionProxy(long id, String name, String code, Date planStart, Date planFinish, int done, String taskState) {
		super(id, name, code, planStart, planFinish, done, taskState);
	}
}
