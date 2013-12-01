package ru.kmz.web.ganttcommon.client;

public interface GanttTaskContextMenuHandler {

	void setPersentDone(long id, int persents);

	void showNewDateSelector(long id);

	void delete(long id);
}
