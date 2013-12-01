package ru.kmz.web.purchases.client;

import java.util.List;

import ru.kmz.web.projectscommon.client.ProjectsCommonServiceAsync;
import ru.kmz.web.projectscommon.shared.ProductElementTaskGridFilter;
import ru.kmz.web.projectscommon.shared.PurchaseProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PurchasesModuleServiceAsync extends ProjectsCommonServiceAsync {

	void getActivePurchases(ProductElementTaskGridFilter filter, AsyncCallback<List<PurchaseProxy>> callback);

	void complitePurchase(long id, AsyncCallback<Void> callback);

}
