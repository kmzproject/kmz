package ru.kmz.server.data.model;

import java.util.Date;
import java.util.List;

import ru.kmz.web.ganttcommon.shared.GraphData;

public interface IProjectTask {

	public GraphData asGraphDataProxy();

	public Date getStart();

	public Date getFinish();

	public void setStart(Date start);

	public void setFinish(Date finish);

	public boolean hasChild();

	public List<ProductElementTask> getChilds();
}
