package com.stg.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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
		System.out.println("Using @BeforeEach annotations ,executed before each test case ");
	}

	@AfterEach
	void tearDown() throws Exception {
		list.clear();
		System.out.println("Using @AfterEach ,executed after each test case");
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

	void delaySecond(int second) {
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	@Disabled("because if this ran it would fail")
	@Test
	@Tag("prod")
	public void m() {
		assertFalse(false);
		System.out.println("Using @Disabled , this execution is ignored");
	}
}
