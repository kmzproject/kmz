package ru.kmz.web.calendar.client.window;

import ru.kmz.web.calendar.client.CalendarModuleView;
import ru.kmz.web.calendar.shared.CalendarRecordProxy;
import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.CommonDirectoryWindow;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class CalendarRecordProperties extends CommonDirectoryWindow<CalendarRecordProxy> {

	private TextField comment;
	private DateField dateField;

	public CalendarRecordProperties() {
		super();
		setPixelSize(500, 150);
		setHeadingText("Выходной/праздничный день");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		dateField = new DateField();
		dateField.setAllowBlank(false);
		p.add(new FieldLabel(dateField, "Дата"), new VerticalLayoutData(1, -1));

		comment = new TextField();
		comment.setAllowBlank(false);
		comment.setEmptyText("Введите комментарий...");
		p.add(new FieldLabel(comment, "Комментарий"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(CalendarRecordProxy object) {
		Info.display("Ошибка", "Невозможно редактировать данные, только удалять и создавать");
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new CalendarRecordProxy();
		object.setDate(dateField.getValue());
		object.setComment(comment.getValue());
		CalendarModuleView.getService().createCalendarRecord(object, new AsyncCallbackWithErrorMessage<Void>(CalendarRecordProperties.this) {
			@Override
			public void onSuccess(Void result) {
				Info.display("Данные сохранены", "");
				if (updatableForm != null)
					updatableForm.update();

			}
		});
	}

}
