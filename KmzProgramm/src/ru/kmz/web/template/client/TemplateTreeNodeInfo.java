package ru.kmz.web.template.client;

import ru.kmz.web.template.shared.TemplateTreeNodeBase;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TemplateTreeNodeInfo implements IsWidget {

	private FlowLayoutContainer container;

	private TextField name;
	private TextField author;
	private TextField genre;

	@Override
	public Widget asWidget() {
		if (container != null)
			return container;
		container = new FlowLayoutContainer();

		// Create the fields
		name = new TextField();
		name.setWidth(200);
		container.add(new FieldLabel(name, "Name"));

		author = new TextField();
		author.setWidth(200);
		container.add(new FieldLabel(author, "Author"));

		genre = new TextField();
		genre.setWidth(200);
		container.add(new FieldLabel(genre, "Genre"));
		return container;
	}

	public void setValue(TemplateTreeNodeBase value) {
		name.setValue(value.getName());
	}
}
