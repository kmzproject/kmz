package ru.kmz.web.common.client.service;

import java.util.List;

import ru.kmz.web.common.shared.HistoryProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("commonModule")
public interface CommonService extends RemoteService {

	List<HistoryProxy> getHistoryByObject(String keyStr);
}
