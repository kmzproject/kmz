package ru.kmz.web.products.client;

import java.util.List;

import ru.kmz.web.projectscommon.shared.ProductProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductsModuleServiceAsync {

	void getActiveProducts(AsyncCallback<List<ProductProxy>> callback);

}
