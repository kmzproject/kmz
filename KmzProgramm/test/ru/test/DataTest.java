package ru.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DataTest {

	protected final static LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@BeforeClass
	public static void setUp() {
		helper.setUp();
	}

	@AfterClass
	public static void tearDown() {
		helper.tearDown();
	}
}
