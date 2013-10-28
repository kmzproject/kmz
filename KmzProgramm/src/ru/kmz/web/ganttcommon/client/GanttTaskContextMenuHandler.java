package ru.kmz.web.ganttcommon.client;

public interface GanttTaskContextMenuHandler {

	void setPersentDone(String id, int persents);

	void showNewDateSelector(String id);

	void delete(String id);
}
