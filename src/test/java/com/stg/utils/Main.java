package com.stg.utils;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Main {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Junit5TestSuiteExample.class);
		System.out.println(result.getRunCount());
	}

}
