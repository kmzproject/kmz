package ru.kmz.web.gant.client.data;

import java.util.List;

public interface IDemoData {

	Task getTasks();
	List<Dependency> getDependencies();
}
