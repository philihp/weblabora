package com.philihp.weblabora.model.building;

public enum BuildingEnum {
	G16(CloisterChapterHouse.class);

	Class<? extends AbstractBuilding> clazz;
	BuildingEnum(Class<? extends AbstractBuilding> clazz) {
		this.clazz = clazz;
	}
}
