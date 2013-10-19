package ru.kmz.web.common.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.info.Info;

public abstract class AsyncCallbackWithErrorMessage<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		Info.display("Ошибка", "При обработке запроса возникла ошибка <br/>" + caught);
	}
}
