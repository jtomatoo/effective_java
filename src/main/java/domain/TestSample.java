package domain;

import org.junit.Test;

public class TestSample {

	@Test
	public static void m1() {}
	
	public static void m2() {}
	
	@Test
	public static void m3() {
		throw new RuntimeException();
	}
	
	@Test
	public void m5() {}
	
	
	public static void m6() {}
	
	@Test
	public static void m7() {
		throw new RuntimeException("fail");
	}
	
	public static void m8() {}
}
