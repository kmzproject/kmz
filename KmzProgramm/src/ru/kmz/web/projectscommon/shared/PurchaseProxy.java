package ru.kmz.web.projectscommon.shared;

import java.util.Date;

@SuppressWarnings("serial")
public class PurchaseProxy extends ProductElementTaskProxy {

	public PurchaseProxy() {
		super();
	}

	public PurchaseProxy(long id, String name, String code, Date planStart, Date planFinish, int done, String taskState) {
		super(id, name, code, planStart, planFinish, done, taskState);
	}
}
