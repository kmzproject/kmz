package ru.kmz.server.data.model;

import java.util.List;

import ru.kmz.web.ganttcommon.shared.GraphData;

public interface IProjectTask {

	public GraphData asGraphDataProxy();

	public boolean hasChild();

	public List<ProductElementTask> getChilds();
}
