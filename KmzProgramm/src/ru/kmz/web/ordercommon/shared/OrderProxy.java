package ru.kmz.web.ordercommon.shared;

import java.io.Serializable;

import ru.kmz.web.common.shared.IHasIdProxy;

@SuppressWarnings("serial")
public class OrderProxy implements Serializable, IHasIdProxy {

	private long id;
	private String name;
	private String customer;
	private String legalNumber;
	private String code;

	public OrderProxy() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNameAndCode() {
		return name + " (" + code + ")";
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public OrderProxy(long id, String code, String name, String customer, String legalNumber) {
		this.id = id;
		this.name = name;
		this.customer = customer;
		this.legalNumber = legalNumber;
		this.code = code;
	}

	public String getLegalNumber() {
		return legalNumber;
	}

	public void setLegalNumber(String value) {
		this.legalNumber = value;
	}

	public String getCode() {
		return code;
	}
}
