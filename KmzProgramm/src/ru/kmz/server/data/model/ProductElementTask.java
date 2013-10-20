package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.server.engine.resources.ResourceTask;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.purchases.shared.PurchaseProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.templatecommon.shared.TemplateTreeNodeFolderProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class ProductElementTask implements IProjectTask {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private int duration;

	@Persistent
	private String resourceType;

	@Persistent
	private Key parentId;

	@Persistent
	private Key orderId;

	@Persistent
	private Date start;
	@Persistent
	private Date finish;

	@Persistent
	private int orderNum;

	@Persistent
	private int done;

	@NotPersistent
	private List<ProductElementTask> childs;

	public void updateData(TemplateTreeNodeBaseProxy proxy) {
		this.name = proxy.getName();
		this.duration = proxy.getDuration();
		this.resourceType = proxy.getResourceType();
	}

	private ProductElementTask(String name, int duration, String resourseType, ResourceTask task) {
		this.name = name;
		this.duration = duration;
		this.resourceType = resourseType;

		if (task != null) {
			this.start = task.getStart();
			this.finish = task.getFinish();
		}

	}

	public ProductElementTask(String name, int duration, String resourseType, ResourceTask task, Order order) {
		this(name, duration, resourseType, task);

		this.parentId = null;
		this.orderId = order.getKey();
	}

	public ProductElementTask(String name, int duration, String resourseType, ResourceTask task, ProductElementTask parent) {
		this(name, duration, resourseType, task);

		this.parentId = parent.getKey();
		this.orderId = parent.getOrderId();

		if (parent.hasChild()) {
			int katenok = parent.getChilds().size();
			this.orderNum = katenok;
		}

		parent.add(this);
	}

	public Key getParentId() {
		return parentId;
	}

	public Key getOrderId() {
		return orderId;
	}

	public String getOrderIdStr() {
		return KeyFactory.keyToString(orderId);
	}

	public void add(ProductElementTask child) {
		if (childs == null) {
			childs = new ArrayList<ProductElementTask>();
		}
		childs.add(child);
		child.parentId = key;
	}

	public Key getKey() {
		return key;
	}

	public String getKeyStr() {
		return KeyFactory.keyToString(key);
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	public TemplateTreeNodeBaseProxy asProxy() {
		if (!hasChild()) {
			TemplateTreeNodeBaseProxy proxy = new TemplateTreeNodeBaseProxy(getKeyStr(), name, duration, resourceType);
			return proxy;
		}

		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(getKeyStr(), name, duration, resourceType);
		for (ProductElementTask child : childs) {
			proxy.add(child.asProxy());
		}
		return proxy;
	}

	public GraphData asGraphDataProxy() {
		GraphData graphData = new GraphData(getKeyStr(), name, duration, resourceType);
		graphData.setComplite(done);
		return graphData;
	}

	public PurchaseProxy asPurchaseProxy() {
		return new PurchaseProxy(getKeyStr(), name, new Date(start.getTime()), new Date(finish.getTime()), done == 100);
	}

	public String getResourceType() {
		return resourceType;
	}

	public List<ProductElementTask> getChilds() {
		return childs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductElementTask) {
			return ((ProductElementTask) obj).key.equals(key);
		}
		return false;
	}

	public Date getStart() {
		return start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setDone(int done) {
		this.done = done;
	}
}
