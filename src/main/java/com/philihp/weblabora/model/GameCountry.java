package com.philihp.weblabora.model;

public enum GameCountry {

	FRANCE, IRELAND;

	public static GameCountry value(String value) {
		if (value == null)
			return null;
		else
			return valueOf(value);
	}
}
