package com.stg.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Test Cases of Calculator")
class CalculatorTest {

	private Calculator calculator;

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
		calculator = new Calculator();
		System.out.println("Using @BeforeEach annotations ,executed before each test case ");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Using @AfterEach ,executed after each test case");
	}

	@Test
	void testAdd() {
		assertEquals(2, calculator.add(1, 1));
	}

	@Test
	void testSub() {
		assertEquals(2, calculator.sub(5, 3));
	}

	@Test
	void testDiv() {
		assertEquals(2, calculator.div(10, 5));
	}
	
	@Test
	void testDiv_exception() {
		Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> calculator.div(10, 0));
		
		assertEquals("Invalid arguments", exception.getMessage());
	}

}
