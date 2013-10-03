package ru.kmz.server.data.utils;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.kmz.server.data.constants.ResourceTypes;
import ru.kmz.server.data.model.Resource;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ResourcesDataUtilsTest1 {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

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
