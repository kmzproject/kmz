package ru.kmz.web.purchases.client;

import java.util.List;

import ru.kmz.web.projectscommon.shared.PurchaseProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PurchasesModuleServiceAsync {

	void getActivePurchases(AsyncCallback<List<PurchaseProxy>> callback);

	void complitePurchase(String id, AsyncCallback<Void> callback);

}
