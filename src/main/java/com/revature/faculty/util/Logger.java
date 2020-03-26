package com.revature.faculty.util;

public class Logger {
	public static final String level = "DEBUG";

	public static void error(Object message) {
		if (level.equals("ERROR")) {
			System.out.println(message);
		}
	}

	public static void info(Object message) {
		if (level.equals("INFO")) {
			System.out.println(message);
		}
	}
}
