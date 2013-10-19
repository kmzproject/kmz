package ru.kmz.web.purchases.client;

import java.util.List;

import ru.kmz.web.purchases.shared.PurchaseProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PurchasesModuleServiceAsync {

	void getActivePurchases(AsyncCallback<List<PurchaseProxy>> callback);

	void complitePurchase(PurchaseProxy proxy, AsyncCallback<Void> callback);

}
