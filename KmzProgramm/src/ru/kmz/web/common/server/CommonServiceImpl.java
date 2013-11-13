package ru.kmz.web.common.server;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.model.History;
import ru.kmz.server.data.utils.HistoryDataUtils;
import ru.kmz.web.common.client.service.CommomService;
import ru.kmz.web.common.shared.HistoryProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CommonServiceImpl extends RemoteServiceServlet implements CommomService {

	@Override
	public List<HistoryProxy> getHistoryByObject(String keyStr) {
		List<History> histories = HistoryDataUtils.getHistoriesByObject(keyStr);
		List<HistoryProxy> list = new ArrayList<HistoryProxy>();
		for (History history : histories) {
			list.add(history.asProxy());
		}
		return list;
	}

}
