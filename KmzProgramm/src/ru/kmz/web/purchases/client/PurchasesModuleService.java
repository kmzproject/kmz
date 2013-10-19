package ru.kmz.web.purchases.client;

import java.util.List;

import ru.kmz.web.purchases.shared.PurchaseProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("purchasesModule")
public interface PurchasesModuleService extends RemoteService {

	List<PurchaseProxy> getActivePurchases();

	void complitePurchase(String id);

}
