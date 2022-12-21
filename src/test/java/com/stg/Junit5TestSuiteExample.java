package com.stg;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@SelectPackages("com.stg")
@ExcludeTags("mainApp")
@Suite
@SuiteDisplayName("A demo Test Suite")
public class Junit5TestSuiteExample {
	
	@Test
	void testMethod() {
		assertTrue(true);
	}
	

}
