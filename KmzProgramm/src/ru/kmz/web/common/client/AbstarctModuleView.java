package ru.kmz.web.common.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstarctModuleView<T extends Widget> implements EntryPoint, IsWidget, IKmzModule {

	protected T container;

	@Override
	public Widget asWidget() {
		if (container == null) {
			createContainer();
		}
		return container;
	}

	protected abstract void createContainer();

	@Override
	public boolean getEnabled() {
		return true;
	}
}
