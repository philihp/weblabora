package com.philihp.weblabora.model.building;

public enum BuildingEnum {
	G16(CloisterChapterHouse.class),
	G07(PeatCoalKiln.class),
	G13(BuildersMarket.class);

	public final Class<? extends AbstractBuilding> clazz;
	BuildingEnum(Class<? extends AbstractBuilding> clazz) {
		this.clazz = clazz;
	}
	
	
}
