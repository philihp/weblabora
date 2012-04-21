package com.philihp.weblabora.model;

public enum TerrainTypeEnum {
	WATER, COAST, PLAINS, HILLSIDE, MOUNTAIN,
	MOOR, FOREST,
	HIDDEN;
	
	public String getProperCase() {
		String name = this.name();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
	}
	
	public String getRowspanAttr() {
		if(this.equals(MOUNTAIN)) {
			return " rowspan=\"2\"";
		}
		else {
			return "";
		}
	}
}
