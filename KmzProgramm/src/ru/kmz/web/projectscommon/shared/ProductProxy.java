package ru.kmz.web.projectscommon.shared;

import java.util.Date;

@SuppressWarnings("serial")
public class ProductProxy extends ProductElementTaskProxy {
	public ProductProxy() {
		super();
	}

	public ProductProxy(long id, String name, String code, Date planStart, Date planFinish, int done, String taskState) {
		super(id, name, code, planStart, planFinish, done, taskState);
	}

}
