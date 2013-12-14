package ru.kmz.web.projectscommon.shared;

import java.io.Serializable;
import java.util.Date;

import ru.kmz.web.common.shared.IHasIdProxy;

@SuppressWarnings("serial")
public class ProductElementTaskProxy implements Serializable, IHasIdProxy {

	private long id;
	private String orderName;
	private String name;
	private Date planStart;
	private Date planFinish;
	private boolean isComplite;
	private int done;
	private String taskState;
	private String code;

	public ProductElementTaskProxy() {
	}

	public ProductElementTaskProxy(long id, String name, String code, Date planStart, Date planFinish, int done, String taskState) {
		this.id = id;
		this.name = name;
		this.planStart = planStart;
		this.planFinish = planFinish;
		this.isComplite = done == 100;
		this.taskState = taskState;
		this.done = done;
		this.code = code;
	}

	public int getDone() {
		return done;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public long getId() {
		return id;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getName() {
		return name;
	}

	public Date getPlanStart() {
		return planStart;
	}

	public Date getPlanFinish() {
		return planFinish;
	}

	public boolean isComplite() {
		return isComplite;
	}

	public String getCode() {
		return code;
	}
}
