package com.stg;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@SelectPackages("com.stg")
@ExcludePackages({"com.stg.repository"})
@ExcludeTags("mainApp")
@Suite
@SuiteDisplayName("A demo Test Suite")
public class Junit5TestSuiteExample {

}
