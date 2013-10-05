package ru.kmz.server.data.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.kmz.server.data.PMF;
import ru.kmz.server.data.model.ProducteTemplateElement;
import ru.kmz.server.data.model.Template;

public class TemplateDataUtils {

	public static Template edit(Template template) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			template = pm.makePersistent(template);
		} finally {
			pm.close();
		}
		return template;
	}

	public static ProducteTemplateElement edit(ProducteTemplateElement element) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(element);
		} finally {
			pm.close();
		}
		return element;
	}

	@SuppressWarnings("unchecked")
	public static List<Template> getAllTemplates() {
		List<Template> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(Template.class);
			list = (List<Template>) query.execute();
			list.size();
		} finally {
			em.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<ProducteTemplateElement> getAllElements() {
		List<ProducteTemplateElement> list = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(ProducteTemplateElement.class);
			list = (List<ProducteTemplateElement>) query.execute();
		} finally {
			em.close();
		}
		return list;
	}

	public static Template getTemplate(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Template template = pm.getObjectById(Template.class, key);
			loadAllChild(pm, template);
			return template;
		} finally {
			pm.close();
		}
	}

	public static ProducteTemplateElement getProducteTemplateElement(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProducteTemplateElement element = pm.getObjectById(ProducteTemplateElement.class, key);
			loadAllChild(pm, element);
			return element;
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	// TODO: передалеть процесс загрузки на получения сначала списка, а уже
	// после загрузки
	private static void loadAllChild(PersistenceManager pm, ProducteTemplateElement element) {
		Query q = pm.newQuery(ProducteTemplateElement.class, " parentId == :parentKey");
		List<ProducteTemplateElement> list = (List<ProducteTemplateElement>) q.execute(element.getKey());
		for (ProducteTemplateElement producteTemplateElement : list) {
			element.add(producteTemplateElement);
			loadAllChild(pm, producteTemplateElement);
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadAllChild(PersistenceManager pm, Template template) {
		Query q = pm.newQuery(ProducteTemplateElement.class, " parentId == null && templateId == :templateKey");
		List<ProducteTemplateElement> list = (List<ProducteTemplateElement>) q.execute(template.getKey());
		template.setRootElement(list.get(0));
		loadAllChild(pm, template.getRootElement());
	}

	public static void deleteProductTemplateElement(String key) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			ProducteTemplateElement element = pm.getObjectById(ProducteTemplateElement.class, key);
			pm.deletePersistent(element);
		} finally {
			pm.close();
		}
	}
}
/*
 * 
 * javax.jdo.JDOUserException: Transient instances cant be deleted. at
 * org.datanucleus
 * .api.jdo.NucleusJDOHelper.getJDOExceptionForNucleusException(NucleusJDOHelper
 * .java:519) at
 * org.datanucleus.api.jdo.JDOPersistenceManager.jdoDeletePersistent
 * (JDOPersistenceManager.java:811) at
 * org.datanucleus.api.jdo.JDOPersistenceManager
 * .deletePersistent(JDOPersistenceManager.java:824) at
 * ru.kmz.server.data.utils.
 * TemplateDataUtils.deleteProductTemplateElement(TemplateDataUtils.java:116) at
 * ru.kmz.web.template.server.TemplateModuleServiceImpl.deleteTemplateTreeNode(
 * TemplateModuleServiceImpl.java:57) at
 * ru.kmz.web.template.server.TemplateModuleServiceImplTest
 * .deleteTest(TemplateModuleServiceImplTest.java:64) at
 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
 * at
 * sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl
 * .java:43) at java.lang.reflect.Method.invoke(Method.java:601) at
 * org.junit.runners
 * .model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:45) at
 * org.junit
 * .internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15) at
 * org
 * .junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java
 * :42) at
 * org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod
 * .java:20) at
 * org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
 * at
 * org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:30)
 * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263) at
 * org.junit.
 * runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:68) at
 * org
 * .junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:
 * 47) at org.junit.runners.ParentRunner$3.run(ParentRunner.java:231) at
 * org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60) at
 * org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229) at
 * org.junit.runners.ParentRunner.access$000(ParentRunner.java:50) at
 * org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222) at
 * org.junit.runners.ParentRunner.run(ParentRunner.java:300) at
 * org.eclipse.jdt.internal
 * .junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50) at
 * org.eclipse
 * .jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38) at
 * org.eclipse
 * .jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner
 * .java:467) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(
 * RemoteTestRunner.java:683) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
 * .run(RemoteTestRunner.java:390) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
 * .main(RemoteTestRunner.java:197) NestedThrowablesStackTrace: Transient
 * instances cant be deleted. org.datanucleus.exceptions.NucleusUserException:
 * Transient instances cant be deleted. at
 * org.datanucleus.ObjectManagerImpl.deleteObjectInternal
 * (ObjectManagerImpl.java:2185) at
 * org.datanucleus.ObjectManagerImpl.deleteObjectWork
 * (ObjectManagerImpl.java:2136) at
 * org.datanucleus.ObjectManagerImpl.deleteObject(ObjectManagerImpl.java:2087)
 * at org.datanucleus.api.jdo.JDOPersistenceManager.jdoDeletePersistent(
 * JDOPersistenceManager.java:806) at
 * org.datanucleus.api.jdo.JDOPersistenceManager
 * .deletePersistent(JDOPersistenceManager.java:824) at
 * ru.kmz.server.data.utils.
 * TemplateDataUtils.deleteProductTemplateElement(TemplateDataUtils.java:116) at
 * ru.kmz.web.template.server.TemplateModuleServiceImpl.deleteTemplateTreeNode(
 * TemplateModuleServiceImpl.java:57) at
 * ru.kmz.web.template.server.TemplateModuleServiceImplTest
 * .deleteTest(TemplateModuleServiceImplTest.java:64) at
 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
 * at
 * sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl
 * .java:43) at java.lang.reflect.Method.invoke(Method.java:601) at
 * org.junit.runners
 * .model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:45) at
 * org.junit
 * .internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15) at
 * org
 * .junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java
 * :42) at
 * org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod
 * .java:20) at
 * org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
 * at
 * org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:30)
 * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263) at
 * org.junit.
 * runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:68) at
 * org
 * .junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:
 * 47) at org.junit.runners.ParentRunner$3.run(ParentRunner.java:231) at
 * org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60) at
 * org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229) at
 * org.junit.runners.ParentRunner.access$000(ParentRunner.java:50) at
 * org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222) at
 * org.junit.runners.ParentRunner.run(ParentRunner.java:300) at
 * org.eclipse.jdt.internal
 * .junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50) at
 * org.eclipse
 * .jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38) at
 * org.eclipse
 * .jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner
 * .java:467) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(
 * RemoteTestRunner.java:683) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
 * .run(RemoteTestRunner.java:390) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
 * .main(RemoteTestRunner.java:197)
 */