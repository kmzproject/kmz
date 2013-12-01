package ru.kmz.web.projectscommon.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectsCommonServiceAsync {

	void setCompliteTaskPersents(long id, int persents, AsyncCallback<Void> callback);

	void setTaskAsStartedPersents(long id, AsyncCallback<Void> callback);

}
