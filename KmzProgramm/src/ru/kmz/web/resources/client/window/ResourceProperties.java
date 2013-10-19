package ru.kmz.web.resources.client.window;

import ru.kmz.web.common.client.AsyncCallbackWithErrorMessage;
import ru.kmz.web.common.client.control.ResourceTypeComboBox;
import ru.kmz.web.common.client.window.CommonDirectoryWindow;
import ru.kmz.web.resources.client.ResourcesModuleView;
import ru.kmz.web.resources.shared.ResourceProxy;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class ResourceProperties extends CommonDirectoryWindow<ResourceProxy> {

	private TextField resourceName;
	private ResourceTypeComboBox resourceType;

	public ResourceProperties() {
		super();
		setPixelSize(500, 150);
		setHeadingText("Ресурс");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		resourceName = new TextField();
		resourceName.setAllowBlank(false);
		resourceName.setEmptyText("Введите название...");
		p.add(new FieldLabel(resourceName, "Название"), new VerticalLayoutData(1, -1));

		resourceType = new ResourceTypeComboBox();

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
		object.setResourceType(resourceType.getValue().getKey());
		ResourcesModuleView.getService().editResource(object, new AsyncCallbackWithErrorMessage<ResourceProxy>() {
			@Override
			public void onSuccess(ResourceProxy result) {
				Info.display("Данные сохранены", result.getName());
				if (updatableForm != null)
					updatableForm.update();
			}
		});
	}

}
