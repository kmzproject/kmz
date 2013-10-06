package ru.kmz.server.data.generator;

import java.util.ArrayList;
import java.util.List;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Resource;
import ru.kmz.server.data.utils.ResourcesDataUtils;

public class ResourceTestData {

	public static List<Resource> createReource1() {
		List<Resource> list = new ArrayList<Resource>();
		Resource r;

		ResourcesDataUtils.edit(r = new Resource("Test1", ResourceTypes.PREPARE));
		list.add(r);
		
		return list;
	}
}
