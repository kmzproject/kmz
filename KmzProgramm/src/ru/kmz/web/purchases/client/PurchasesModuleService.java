package ru.kmz.web.purchases.client;

import java.util.List;

import ru.kmz.web.projectscommon.shared.PurchaseProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsCommon.rpc")
public interface PurchasesModuleService extends RemoteService {

	List<PurchaseProxy> getActivePurchases();

	void complitePurchase(String id);

}
