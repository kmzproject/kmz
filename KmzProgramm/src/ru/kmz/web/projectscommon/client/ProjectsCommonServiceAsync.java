package ru.kmz.web.projectscommon.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectsCommonServiceAsync {

	void setCompliteTaskPersents(String id, int persents, AsyncCallback<Void> callback);

}
