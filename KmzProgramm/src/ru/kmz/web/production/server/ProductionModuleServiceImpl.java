package ru.kmz.web.production.server;

import java.util.List;

import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.production.ProductionListUtils;
import ru.kmz.web.production.client.ProductionModuleService;
import ru.kmz.web.production.shared.ProductionProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ProductionModuleServiceImpl extends RemoteServiceServlet implements ProductionModuleService {

	@Override
	public void compliteProduction(String id) {
		ProductElementTaskDataUtils.setTaskComplitePersents(id, 100);
	}

	@Override
	public List<ProductionProxy> getActiveProductions() {
		return ProductionListUtils.getActiveProduction();
	}

}
