package com.stg.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Test Cases of junit methods")
public class JunitMethodsDemoTest {
	private ArrayList<String> list;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Using @BeforeAll , executed before all test cases ");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Using @AfterAll ,executed after all test cases");
	}

	@BeforeEach
	void setUp() throws Exception {
		list = new ArrayList<>();
		MockitoAnnotations.openMocks(this);
		System.out.println("Using @BeforeEach annotations ,executed before each test case ");
	}

	@AfterEach
	void tearDown() throws Exception {
		list.clear();
		System.out.println("Using @AfterEach ,executed after each test case");
	}

	@Test
	@Tag("dev")
	void greetingsTest() {
		JunitMethodsDemo junitMethodsDemo = new JunitMethodsDemo();
		String name = "Raghu";
		assertEquals("Welcome " + name, junitMethodsDemo.greetings(name));
	}

	@Test
	@Tag("dev")
	void junitMethodsTest() {
		list.add("test");
//		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		// assertEquals(2, list.size(), "expecting 1 list of size-1");
//		assertTrue(true);
	}

	@Test
	@Tag("dev")
	public void m1() {
		assertTimeout(Duration.ofSeconds(5), () -> delaySecond(2));
		System.out.println("we can use @Timeout to fail a test if the execution time exceeds a given duration.");
	}

	void delaySecond(int second) throws Exception {
		TimeUnit.SECONDS.sleep(second);
	}

	@Test
	@Tag("prod")
	@Timeout(5)
	// @Timeout(value = 5, unit = TimeUnit.MILLISECONDS)
	public void m2() {
		System.out.println("we can use @Timeout to fail a test if the execution time exceeds a given duration.");
	}

	@Test
	@Tag("exception")
	public void m3() {
		Exception exception = assertThrows(IOException.class, () -> {
			throw new IOException("We interrupt this test to throw an checked exception");
		});
		assertEquals("We interrupt this test to throw an checked exception", exception.getMessage());
	}

	@Test
	@Tag("exception")
	public void m4() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			throw new NullPointerException("We interrupt this test to throw an runtime exception");
		});
		assertEquals("We interrupt this test to throw an runtime exception", exception.getMessage());
	}

	// @Disabled("because if this ran it would fail")
	@Test
	@Tag("prod")
	public void m() {
		assertFalse(false);
		System.out.println("Using @Disabled , this execution is ignored");
	}

	@Spy
	List<String> spyList2 = new ArrayList<>();

	/*
	 * @Test public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
	 * System.out.println(spyList2); spyList2.add("one"); spyList2.add("two");
	 * 
	 * 
	 * Mockito.verify(spyList2).add("one"); Mockito.verify(spyList2).add("two");
	 * 
	 * assertEquals(2, spyList2.size()); }
	 */

	/*
	 * @Test public void spyExample() {
	 * 
	 * List<String> spyList1 = Mockito.spy(new ArrayList<String>());
	 * spyList1.add("one"); spyList1.add("two");
	 * 
	 * Mockito.verify(spyList1).add("one"); Mockito.verify(spyList1).add("two");
	 * 
	 * assertEquals(2, spyList1.size()); }
	 */

}
