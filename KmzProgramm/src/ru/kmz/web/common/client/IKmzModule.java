package ru.kmz.web.common.client;

import com.google.gwt.user.client.ui.IsWidget;

public interface IKmzModule extends IsWidget {

	String getModuleName();

	boolean getEnabled();
}
