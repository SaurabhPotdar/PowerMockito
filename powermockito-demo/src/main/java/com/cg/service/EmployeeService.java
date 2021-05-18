package com.cg.service;

import java.util.Date;

public class EmployeeService {

	public static String staticMethod() {
		return "Hello World";
	}

	public String callStatic() {
		return EmployeeService.staticMethod();
	}

	public String callPrivate() {
		return "Mock private method example: " + privateMethod();
	}

	private String privateMethod() {
		return new Date().toString();
	}
	
	public final String callFinal() {
		return "Mock final method example: " + finalMethod();
	}

	private String finalMethod() {
		return new Date().toString();
	}

}
