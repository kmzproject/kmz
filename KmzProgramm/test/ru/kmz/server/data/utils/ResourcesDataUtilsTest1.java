package ru.kmz.server.data.utils;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Resource;
import ru.test.DataTest;

public class ResourcesDataUtilsTest1 extends DataTest {

	@Test
	public void TestCreateResource1() {
		List<Resource> listBeforeAdd = ResourcesDataUtils.getAllResources();

		Resource r = new Resource("Test1", ResourceTypes.ORDER);
		r = ResourcesDataUtils.edit(r);
		List<Resource> listAfterAdd = ResourcesDataUtils.getAllResources();
		Assert.assertEquals(listBeforeAdd.size() + 1, listAfterAdd.size());

		ResourcesDataUtils.delete(r.getKey().getId());
		List<Resource> listAfterDelete = ResourcesDataUtils.getAllResources();
		Assert.assertEquals(listBeforeAdd.size(), listAfterDelete.size());

	}
}
