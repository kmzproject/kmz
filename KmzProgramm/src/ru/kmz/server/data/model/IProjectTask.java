package ru.kmz.server.data.model;

import java.util.Date;
import java.util.List;

import ru.kmz.web.ganttcommon.shared.GraphData;

public interface IProjectTask {

	public Long getId();

	public GraphData asGraphDataProxy();

	public Date getStart();

	public Date getFinish();

	public void setStart(Date start);

	public void setFinish(Date finish);

	public int getDone();

	public void setDone(int done);

	public int getDuration();

	public boolean hasChild();

	public boolean isFolder();

	public List<ProductElementTask> getChilds();
}
