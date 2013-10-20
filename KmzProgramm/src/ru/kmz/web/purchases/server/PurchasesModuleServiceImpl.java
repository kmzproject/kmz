package ru.kmz.web.purchases.server;

import java.util.List;

import ru.kmz.server.data.utils.ProductElementTaskDataUtils;
import ru.kmz.server.engine.purchases.PurchasesListUtils;
import ru.kmz.web.purchases.client.PurchasesModuleService;
import ru.kmz.web.purchases.shared.PurchaseProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class PurchasesModuleServiceImpl extends RemoteServiceServlet implements PurchasesModuleService {

	@Override
	public List<PurchaseProxy> getActivePurchases() {
		return PurchasesListUtils.getActivePurchases();
	}

	@Override
	public void complitePurchase(String id) {
		ProductElementTaskDataUtils.setTaskComplitePersents(id, 100);
	}

}
