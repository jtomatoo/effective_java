package domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import domain.anno.ExceptionTest;
import domain.anno.ExceptionTestContainer;

public class AnnotationTest {

	@Test
	public void simpleTest() throws Exception {
		int test = 0;
		int passed = 0;
		Class<?> testClass = Class.forName("domain.TestSample");
		for (Method m : testClass.getDeclaredMethods()) {
			test++;
			try {
				m.invoke(null);
				passed++;
			} catch(InvocationTargetException wrappedExc) {
				Throwable exc = wrappedExc.getCause();
				System.out.println(m + "fail: " + exc);
			} catch (Exception e) {
				System.out.println("incorrect used @test: " + m);
			}
		}
		System.out.printf("success: %d, fail: %d%n", passed, (test - passed));
	}
	
	@Test
	public void simpleTest2() throws Exception {
		System.out.println("simpleTest2>>");
		int test = 0;
		int passed = 0;
		Class<?> testClass = Class.forName("domain.TestSample2");
		for (Method m : testClass.getMethods()) {
			if(m.isAnnotationPresent(ExceptionTest.class)
					|| m.isAnnotationPresent(ExceptionTestContainer.class)) {
//			if(m.isAnnotationPresent(ExceptionTest.class)) {
				test++;
				try {
					m.invoke(null);
					System.out.printf("test %s fail: not throw exception%n", m);
				} catch(Throwable wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					int oldPassed = passed;
					
					ExceptionTest[] excTests = m.getAnnotationsByType(ExceptionTest.class);
					for (ExceptionTest excTest : excTests) {
						if(excTest.value().isInstance(exc)) {
							passed++;
							break;
						}
						if(passed == oldPassed) {
							System.out.printf("test %s fail: %s %n", m, exc);
						}
					}
					/*
					Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionTest.class).value();
					for (Class<? extends Throwable> excType : excTypes) {
						if(excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
					
					if(passed == oldPassed) {
						System.out.printf("test %s fail: %s %n", m, exc);
					}
					*/
				}
				/*catch(InvocationTargetException wrappedEx) {
					Throwable exc = wrappedEx.getCause();
					Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
					if(excType.isInstance(exc)) {
						passed++;
					} else {
						System.out.printf("test %s fail: expected exception %s, occured exception %s%n", m, excType.getName(), exc);
					}
				} catch (Exception e) {
					System.out.println("incorrect used @ExceptionTest: " + m);
				}*/
			}
		}
		System.out.printf("success: %d, fail: %d%n", passed, (test - passed));
	}
}
