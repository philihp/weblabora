package com.philihp.weblabora.model.building;

public enum BuildingEnum {
	G07(PeatCoalKiln.class),
	G16(CloisterChapterHouse.class),
	G19(Slaughterhouse.class),
	F24(CloisterChurch.class),
	G22(Quarry.class),
	G26(Shipyard.class),
	F27(Palace.class),
	G34(Sacristy.class);

	public final Class<? extends AbstractBuilding> clazz;
	BuildingEnum(Class<? extends AbstractBuilding> clazz) {
		this.clazz = clazz;
	}
	
	
}
