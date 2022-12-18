package com.stg.utils;

import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SelectPackages("com.stg")
@IncludePackages("com.stg.utils")
@SelectClasses({CalculatorTest.class})
@Suite
@SuiteDisplayName("A demo Test Suite")
public class Junit5TestSuiteExample {

}
