package com.philihp.weblabora.model;

public enum TerrainTypeEnum {
	WATER, COAST, PLAINS, HILLSIDE, MOUNTAIN,
	MOOR, FOREST;
	
	public String getProperCase() {
		String name = this.name();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
	}
}
