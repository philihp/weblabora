package com.philihp.weblabora.model;

public enum GameLength {

	LONG, SHORT, NULL;

	public static GameLength value(String value) {
		if (value == null)
			return NULL;
		else
			return valueOf(value);
	}
}
