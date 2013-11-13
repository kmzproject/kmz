package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.engine.resources.ResourceTask;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.production.shared.ProductionProxy;
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
	private String code;

	@Persistent
	private int number;

	@Persistent
	private int durationWork;

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

	@Persistent
	private int count;

	@NotPersistent
	private List<ProductElementTask> childs;

	public void updateData(TemplateTreeNodeBaseProxy proxy) {
		this.name = proxy.getName();
		this.durationWork = proxy.getDuration();
		this.resourceType = proxy.getResourceType();
	}

	private ProductElementTask(String name, int durationWork, String resourseType, ResourceTask task) {
		this.name = name;
		this.resourceType = resourseType;
		this.durationWork = durationWork;

		if (task != null) {
			this.start = task.getStart();
			this.finish = task.getFinish();
		}

	}

	public ProductElementTask(String name, int durationWork, String resourseType, ResourceTask task, Order order) {
		this(name, durationWork, resourseType, task);

		this.parentId = null;
		this.orderId = order.getKey();
	}

	public ProductElementTask(String name, int durationWork, String resourseType, ResourceTask task, ProductElementTask parent) {
		this(name, durationWork, resourseType, task);

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

	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	public TemplateTreeNodeBaseProxy asProxy() {
		if (!hasChild()) {
			TemplateTreeNodeBaseProxy proxy = new TemplateTreeNodeBaseProxy(getKeyStr(), name, durationWork, resourceType);
			return proxy;
		}

		TemplateTreeNodeFolderProxy proxy = new TemplateTreeNodeFolderProxy(getKeyStr(), name, durationWork, resourceType);
		for (ProductElementTask child : childs) {
			proxy.add(child.asProxy());
		}
		return proxy;
	}

	private String getNameAndCount() {
		if (count == 1) {
			return name;
		}
		return count + "x " + name;
	}

	public GraphData asGraphDataProxy() {
		int duration = DateUtils.diffInDays(start, finish);
		GraphData graphData = new GraphData(getKeyStr(), getNameAndCount(), duration, durationWork, resourceType);
		graphData.setComplite(done);
		return graphData;
	}

	public PurchaseProxy asPurchaseProxy() {
		return new PurchaseProxy(getKeyStr(), name, code, new Date(start.getTime()), new Date(finish.getTime()), done == 100);
	}

	public ProductionProxy asProductionProxy() {
		return new ProductionProxy(getKeyStr(), name, code, new Date(start.getTime()), new Date(finish.getTime()), done == 100);
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

	public int getDone() {
		return done;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setNumberInfo(int number, String orderCode) {
		this.number = number;
		code = "000" + number;
		code = code.substring(code.length() - 3);
		String prefix = "0";
		if (resourceType.equals(ResourceTypes.ASSEMBLAGE)) {
			prefix = "A";
		} else if (resourceType.equals(ResourceTypes.PREPARE)) {
			prefix = "B";
		} else if (resourceType.equals(ResourceTypes.FOLDER)) {
			prefix = "F";
		} else if (resourceType.equals(ResourceTypes.PRODUCT)) {
			prefix = "I";
		} else if (resourceType.equals(ResourceTypes.ORDER)) {
			prefix = "D";
		}
		code = prefix + "-" + orderCode.substring(2) + code;
	}

	public String getCode() {
		return code;
	}

	public int getNumber() {
		return number;
	}

	public void addOffset(int offset) {
		finish = DateUtils.getOffsetDate(finish, offset);
		start = DateUtils.getOffsetDate(start, offset);
	}
}
