package ru.kmz.web.common.client;

import ru.kmz.web.common.shared.HasResourceType;
import ru.kmz.web.common.shared.ResourceTypesConsts;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;

public class TreeIconProvider<T extends HasResourceType> implements IconProvider<T> {

	@Override
	public ImageResource getIcon(T node) {
		if (node.getResourceType() == null)
			return null;
		if (node.getResourceType().equals(ResourceTypesConsts.ASSEMBLAGE))
			return TemplateTreeNodeImages.INSTANCE.assemblage();
		if (node.getResourceType().equals(ResourceTypesConsts.PREPARE))
			return TemplateTreeNodeImages.INSTANCE.prepare();
		if (node.getResourceType().equals(ResourceTypesConsts.ORDER))
			return TemplateTreeNodeImages.INSTANCE.order();
		if (node.getResourceType().equals(ResourceTypesConsts.FOLDER))
			return TemplateTreeNodeImages.INSTANCE.folder();
		return null;
	}

}
