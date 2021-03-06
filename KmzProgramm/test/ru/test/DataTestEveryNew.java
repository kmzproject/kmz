package ru.test;

import org.junit.After;
import org.junit.Before;

import ru.kmz.server.data.utils.CacheUtils;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DataTestEveryNew {

	protected final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		CacheUtils.cleanAllCaches();
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
}
