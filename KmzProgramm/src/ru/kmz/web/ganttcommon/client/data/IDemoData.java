package ru.kmz.web.ganttcommon.client.data;

import java.util.List;

public interface IDemoData {

	Task getTasks();
	List<Dependency> getDependencies();
}
