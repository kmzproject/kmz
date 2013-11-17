package ru.kmz.web.products.client;

import java.util.List;

import ru.kmz.web.projectscommon.shared.ProductProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectsCommon.rpc")
public interface ProductsModuleService extends RemoteService {

	List<ProductProxy> getActiveProducts();

}
