package ru.kmz.web.common.client.service;

import java.util.List;

import ru.kmz.web.common.shared.HistoryProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommonServiceAsync {

	void getHistoryByObject(String keyStr, AsyncCallback<List<HistoryProxy>> callback);

}
