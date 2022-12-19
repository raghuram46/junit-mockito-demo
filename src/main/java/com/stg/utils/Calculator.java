package com.stg.utils;

public class Calculator {
	
	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	public int div(int a, int b) {
		if (a > 0 && b > 0) {
			return a / b;
		} else {
			throw new ArrayIndexOutOfBoundsException("Invalid arguments");
		}

	}
}
