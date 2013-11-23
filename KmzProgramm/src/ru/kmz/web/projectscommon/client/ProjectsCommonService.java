package ru.kmz.web.projectscommon.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsCommon.rpc")
public interface ProjectsCommonService extends RemoteService {

	void setCompliteTaskPersents(String id, int persents);

	void setTaskAsStartedPersents(String id);

}
