package com.philihp.weblabora.util;

public class IntegerUtil {
	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
