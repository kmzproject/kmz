package ru.kmz.web.resources.client;

import ru.kmz.web.common.client.window.CommonDirectoryWindow;
import ru.kmz.web.resources.shared.ResourceProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class ResourceProperties extends CommonDirectoryWindow<ResourceProxy> {

	private TextField resourceName;
	private TextField resourceType;

	public ResourceProperties() {
		super();
		setPixelSize(500, 200);
		setHeadingText("Ресурс");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		resourceName = new TextField();
		resourceName.setAllowBlank(false);
		resourceName.setEmptyText("Введите название...");
		p.add(new FieldLabel(resourceName, "Название"), new VerticalLayoutData(1, -1));

		resourceType = new TextField();
		resourceType.setAllowBlank(false);
		resourceType.setEmptyText("Введите тип ресурса...");
		p.add(new FieldLabel(resourceType, "Тип ресурса"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(ResourceProxy object) {
		this.object = object;
		resourceName.setValue(object.getName());
		resourceType.setValue(object.getResourceType());
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new ResourceProxy();
		object.setName(resourceName.getValue());
		object.setResourceType(resourceType.getValue());
		ResourcesModuleView.getService().editResource(object, new AsyncCallback<ResourceProxy>() {

			@Override
			public void onSuccess(ResourceProxy result) {
				Info.display("Данные сохранены", result.getName());
				if (updatableForm != null)
					updatableForm.update();
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Ошибка", "Ошибка при сохранении<br/>" + caught.getMessage());
			}
		});
	}

}
