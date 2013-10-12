package ru.kmz.server.engine.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.kmz.server.engine.resources.ResourceTask;
import ru.kmz.server.utils.DateUtils;

public class ResourceTaskTest {

	@Test
	public void TestSort() {
		List<ResourceTask> list = new ArrayList<ResourceTask>();
		list.add(new ResourceTask(DateUtils.getDate("2013/11/30"), 0));
		list.add(new ResourceTask(DateUtils.getDate("2013/11/25"), 0));
		list.add(new ResourceTask(DateUtils.getDate("2013/10/08"), 0));
		list.add(new ResourceTask(DateUtils.getDate("2013/11/30"), 0));
		list.add(new ResourceTask(DateUtils.getDate("2013/12/11"), 0));
		Collections.sort(list);
		Assert.assertEquals(list.get(0).getStart(), DateUtils.getDate("2013/10/08"));
		Assert.assertEquals(list.get(1).getStart(), DateUtils.getDate("2013/11/25"));
		Assert.assertEquals(list.get(2).getStart(), DateUtils.getDate("2013/11/30"));
		Assert.assertEquals(list.get(3).getStart(), DateUtils.getDate("2013/11/30"));
		Assert.assertEquals(list.get(4).getStart(), DateUtils.getDate("2013/12/11"));
	}
}
