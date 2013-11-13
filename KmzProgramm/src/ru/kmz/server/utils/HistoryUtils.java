package ru.kmz.server.utils;

import java.util.Date;

import ru.kmz.server.data.model.History;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.HistoryDataUtils;

import com.google.appengine.api.datastore.Key;

public class HistoryUtils {

	private static interface HistoryName {
		String SET_FACT = "Проставлен факт";
	}

	public static void setFact(ProductElementTask task) {
		createHistory(task.getKey(), HistoryName.SET_FACT, "" + task.getDone());

	}

	private static void createHistory(Key key, String name, String comment) {
		History history = new History();
		history.setDate(new Date());
		history.setObjectId(key);
		history.setComment(comment);
		history.setName(name);

		history = HistoryDataUtils.edit(history);
	}
}
