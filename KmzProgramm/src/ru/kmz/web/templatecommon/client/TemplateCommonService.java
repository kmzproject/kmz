package ru.kmz.web.templatecommon.client;

import java.util.List;

import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateCommon.rpc")
public interface TemplateCommonService extends RemoteService {

	List<TemplateTreeDataProxy> getTemplateList();

}
