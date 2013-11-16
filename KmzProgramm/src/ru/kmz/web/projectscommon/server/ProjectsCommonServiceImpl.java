package ru.kmz.web.projectscommon.server;

import java.util.List;

import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.purchases.PurchasesListUtils;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;
import ru.kmz.web.purchases.client.PurchasesModuleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ProjectsCommonServiceImpl extends RemoteServiceServlet implements PurchasesModuleService {

	@Override
	public List<PurchaseProxy> getActivePurchases() {
		return PurchasesListUtils.getActivePurchases();
	}

	@Override
	public void complitePurchase(String id) {
		ProductElementTaskDataUtils.setTaskComplitePersents(id, 100);
	}

}
