package ru.kmz.web.template.client.window;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.window.CommonDirectoryWindow;
import ru.kmz.web.template.client.TemplateModuleView;
import ru.kmz.web.templatecommon.shared.TemplateTreeDataProxy;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class TemplateNameWindow extends CommonDirectoryWindow<TemplateTreeDataProxy> {

	private TextField templateName;

	public TemplateNameWindow() {
		super();
		setPixelSize(500, 150);
		setHeadingText("Шаблон");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		templateName = new TextField();
		templateName.setAllowBlank(false);
		templateName.setEmptyText("Введите название...");
		p.add(new FieldLabel(templateName, "Название"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(TemplateTreeDataProxy object) {
		this.object = object;
		templateName.setValue(object.getName());
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new TemplateTreeDataProxy();
		object.setName(templateName.getValue());
		TemplateModuleView.getService().editTemplate(object, new AsyncCallbackWithErrorMessage<Void>() {
			@Override
			public void onSuccess(Void result) {
				Info.display("Данные сохранены", object.getName());
			}
		});
	}

}
