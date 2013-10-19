package ru.kmz.web.common.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface TemplateTreeNodeImages extends ClientBundle {
	public TemplateTreeNodeImages INSTANCE = GWT.create(TemplateTreeNodeImages.class);

	@Source("order.png")
	ImageResource order();

	@Source("prepare.png")
	ImageResource prepare();

	@Source("assemblage.png")
	ImageResource assemblage();

	@Source("folder.png")
	ImageResource folder();

	@Source("product.png")
	ImageResource product();

	@Source("productOrder.png")
	ImageResource productOrder();
}
