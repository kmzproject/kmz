package ru.kmz.server.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.server.data.constants.ProductElementTaskStates;
import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.utils.DateUtils;
import ru.kmz.web.common.shared.ResourceTypesConsts;
import ru.kmz.web.common.shared.TemplateTreeNodeBaseProxy;
import ru.kmz.web.ganttcommon.shared.GraphData;
import ru.kmz.web.projectscommon.shared.ProductElementTaskProxy;
import ru.kmz.web.projectscommon.shared.ProductProxy;
import ru.kmz.web.projectscommon.shared.ProductionProxy;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

@PersistenceCapable
public class ProductElementTask implements IProjectTask {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;

	@Persistent
	private String code;

	@Persistent
	/** Состояние работы (@see ru.kmz.server.data.constants.ProductElementTaskStates) */
	private String taskState;

	@Persistent
	/** Номер работы, для вычисленя кода */
	private int number;

	@Persistent
	/** Время работы */
	private int durationWork;

	@Persistent
	private String resourceType;

	@Persistent
	private Long parentId;

	@Persistent
	private Long orderId;

	@Persistent
	/** Плановая дата начала */
	private Date start;
	@Persistent
	/** Плановая дата финиша */
	private Date finish;

	@Persistent
	/** Номер для сортировки */
	private int orderNum;

	@Persistent
	/** Процент выполнения */
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

	public ProductElementTask(ProductTemplateElement template, Long orderId) {
		this.name = template.getName();
		this.resourceType = template.getResourceType();
		this.durationWork = template.getDuration();

		this.parentId = null;
		this.taskState = ProductElementTaskStates.PLANNED;

		this.orderId = orderId;
	}

	public ProductElementTask(ProductTemplateElement template, ProductElementTask parent) {
		this(template, parent.getOrderId());

		this.parentId = parent.getId();

		if (parent.hasChild()) {
			int katenok = parent.getChilds().size();
			this.orderNum = katenok;
		}
		parent.add(this);

	}

	public Long getParentId() {
		return parentId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void add(ProductElementTask child) {
		if (childs == null) {
			childs = new ArrayList<ProductElementTask>();
		}
		childs.add(child);
		child.parentId = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean hasChild() {
		return childs != null && childs.size() != 0;
	}

	private String getNameAndCount() {
		if (count == 1) {
			return name;
		}
		return name + " [" + count + "]";
	}

	public GraphData asGraphDataProxy() {
		int duration = DateUtils.diffInDays(start, finish);
		GraphData graphData = new GraphData(id, getNameAndCount(), code, duration, durationWork, resourceType, done);
		return graphData;
	}

	public ProductElementTaskProxy asProxy() {
		return new ProductElementTaskProxy(id, name, code, new Date(start.getTime()), new Date(finish.getTime()), done, taskState);
	}

	public PurchaseProxy asPurchaseProxy() {
		return new PurchaseProxy(id, name, code, new Date(start.getTime()), new Date(finish.getTime()), done, taskState);
	}

	public ProductionProxy asProductionProxy() {
		return new ProductionProxy(id, name, code, new Date(start.getTime()), new Date(finish.getTime()), done, taskState);
	}

	public ProductProxy asProductProxy() {
		return new ProductProxy(id, name, code, new Date(start.getTime()), new Date(finish.getTime()), done, taskState);
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
			return ((ProductElementTask) obj).id.equals(id);
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
		if (done == 100) {
			this.taskState = ProductElementTaskStates.FINISHED;
		} else if (done > 0) {
			this.taskState = ProductElementTaskStates.STARTED;
		}
		this.done = done;
	}

	public boolean setTaskStateAsStarted() {
		if (taskState == null || !taskState.equals(ProductElementTaskStates.STARTED)) {
			this.taskState = ProductElementTaskStates.STARTED;
			return true;
		}
		return false;
	}

	public boolean setTaskStatePlanned() {
		if (taskState != null && (taskState.equals(ProductElementTaskStates.STARTED) || taskState.equals(ProductElementTaskStates.FINISHED))) {
			this.taskState = ProductElementTaskStates.PLANNED;
			return true;
		}
		return false;
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
		} else if (resourceType.equals(ResourceTypes.PURCHASE)) {
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

	@Override
	public String toString() {
		return code + " " + getNameAndCount();
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	@Override
	public int getDuration() {
		return durationWork;
	}

	@Override
	public boolean isFolder() {
		return ResourceTypesConsts.isFolder(getResourceType());
	}

}
