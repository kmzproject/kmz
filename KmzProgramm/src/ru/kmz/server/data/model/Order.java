package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.calculator.shared.OrderProxy;
import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.shared.GraphData;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Order implements IProjectTask {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@NotPersistent
	private List<ProductElementTask> childs;

	public Order() {
	}

	public Order(String name) {
		this.name = name;
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
		return new OrderProxy(KeyFactory.keyToString(key), name);
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
		return new GraphData(key.getId() + "", name, 0, ResourceTypesConsts.FOLDER);
	}

	@Override
	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

}
