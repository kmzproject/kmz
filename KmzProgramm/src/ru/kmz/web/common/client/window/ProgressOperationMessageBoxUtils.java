package ru.kmz.web.common.client.window;

import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;

public class ProgressOperationMessageBoxUtils {

	public static AutoProgressMessageBox getServerOperation() {
		AutoProgressMessageBox box = new AutoProgressMessageBox("Выполнение операции", "Это может занять некоторое время");
		box.setProgressText("Обработка...");
		box.auto();
		return box;
	}

	public static AutoProgressMessageBox getServerRequest() {
		AutoProgressMessageBox box = new AutoProgressMessageBox("Запрос данных на сервере", "Это может занять некоторое время");
		box.setProgressText("Запрос...");
		box.auto();
		return box;
	}
}
