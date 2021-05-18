package com.cg.service;

import java.util.Date;

public class EmployeeService {

	public static String get() {
		return "Hello World";
	}

	public String callStatic() {
		return EmployeeService.get();
	}

	public String getDetails() {
		return "Mock private method example: " + iAmPrivate();
	}

	private String iAmPrivate() {
		return new Date().toString();
	}

}
