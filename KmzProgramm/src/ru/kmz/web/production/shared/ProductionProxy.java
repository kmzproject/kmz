package ru.kmz.web.production.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ProductionProxy implements Serializable {

	private String id;
	private String orderName;
	private String name;
	private Date planStart;
	private Date planFinish;
	private boolean isComplite;

	public ProductionProxy() {
	}

	public ProductionProxy(String id, String name, Date planStart, Date planFinish, boolean isComplite) {
		this.id = id;
		this.name = name;
		this.planStart = planStart;
		this.planFinish = planFinish;
		this.isComplite = isComplite;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getId() {
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
}
