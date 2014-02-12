package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.ordercommon.shared.OrderProxy;

@PersistenceCapable
public class Order implements IProjectTask, Cloneable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

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

	@Persistent
	private Date start;
	@Persistent
	private Date finish;
	@Persistent
	private int done;

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

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderProxy asProxy() {
		return new OrderProxy(id, code, name, customer, legalNumber);
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
		int duration = 0;
		if (start != null && finish != null) {
			duration = DateUtils.diffInDays(start, finish);
		}
		return new GraphData(id, name, code, duration, duration, ResourceTypesConsts.ORDER, done);
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

	@Override
	public Date getStart() {
		if (start == null) {
			return DateUtils.getDateNoTime(new Date());
		}
		return start;
	}

	@Override
	public Date getFinish() {
		if (finish == null) {
			return DateUtils.getDateNoTime(new Date());
		}
		return finish;
	}

	@Override
	public void setStart(Date start) {
		this.start = start;
	}

	public boolean isEmptyTime() {
		return start == null || finish == null;
	}

	@Override
	public void setFinish(Date finish) {
		this.finish = finish;
	}

	@Override
	public int getDone() {
		return done;
	}

	@Override
	public int getDuration() {
		throw new IllegalArgumentException();
	}

	@Override
	public void setDone(int done) {
		this.done = done;
	}

	@Override
	public boolean isFolder() {
		return true;
	}

	public String getNameAndCode() {
		return getName() + " (" + getCode() + ")";
	}

	@Override
	public Object clone() {
		try {
			Order clone = (Order) super.clone();
			clone.childs = null;
			return clone;
		} catch (CloneNotSupportedException ex) {
			return null;
		}

	}
}
