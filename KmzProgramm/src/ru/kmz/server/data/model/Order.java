package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.ordercommon.shared.OrderProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Order implements IProjectTask {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;
	@Persistent
	private String customer;
	@Persistent
	private String legalNumber;

	@Persistent
	private int number;

	@Persistent
	private String code;

	@NotPersistent
	private List<ProductElementTask> childs;

	public Order() {
	}

	public Order(String name, String customer, String legalNumber) {
		this.name = name;
		this.customer = customer;
		this.legalNumber = legalNumber;
	}

	public void updateData(OrderProxy proxy) {
		this.name = proxy.getName();
		this.customer = proxy.getCustomer();
		this.legalNumber = proxy.getLegalNumber();
	}

	public Key getKey() {
		return key;
	}

	public String getKeyStr() {
		return KeyFactory.keyToString(key);
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderProxy asProxy() {
		return new OrderProxy(KeyFactory.keyToString(key), code, name, customer, legalNumber);
	}

	public List<ProductElementTask> getChilds() {
		return childs;
	}

	public void add(ProductElementTask child) {
		if (childs == null) {
			childs = new ArrayList<ProductElementTask>();
		}
		childs.add(child);
	}

	@Override
	public GraphData asGraphDataProxy() {
		return new GraphData(getKeyStr(), name, code, 0, 0, ResourceTypesConsts.ORDER);
	}

	@Override
	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	public String getCode() {
		return code;
	}

	public void setNumber(int number) {
		this.number = number;
		code = "000" + number;
		code = "Z-" + code.substring(code.length() - 3);
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return code + " " + name + " " + customer + " " + legalNumber;
	}
}
