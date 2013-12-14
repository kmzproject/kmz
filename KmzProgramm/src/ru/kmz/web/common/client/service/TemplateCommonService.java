package ru.kmz.web.common.client.service;

import java.util.List;

import ru.kmz.web.common.shared.TemplateTreeDataProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("templateCommon.rpc")
public interface TemplateCommonService extends RemoteService {

	List<TemplateTreeDataProxy> getTemplateList();

}
