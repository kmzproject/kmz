package ru.kmz.web.common.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.History;
import ru.kmz.server.data.utils.HistoryDataUtils;
import ru.kmz.web.common.client.service.CommonService;
import ru.kmz.web.common.shared.HistoryProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CommonServiceImpl extends RemoteServiceServlet implements CommonService {

	@Override
	public List<HistoryProxy> getHistoryByObject(String keyStr) {
		List<History> histories;
		if (keyStr != null) {
			histories = HistoryDataUtils.getHistoriesByObject(keyStr);
		} else {
			histories = HistoryDataUtils.getLastHistories();
		}
		List<HistoryProxy> list = new ArrayList<HistoryProxy>();
		for (History history : histories) {
			list.add(history.asProxy());
		}
		return list;
	}

}